package com.court.digitalcourtmanagement.Controller;

import com.court.digitalcourtmanagement.dto.LoginRequest;
import com.court.digitalcourtmanagement.dto.LoginResponse;
import com.court.digitalcourtmanagement.dto.RegisterRequest;
import com.court.digitalcourtmanagement.entity.AppUser;
import com.court.digitalcourtmanagement.entity.Client;
import com.court.digitalcourtmanagement.entity.Role;
import com.court.digitalcourtmanagement.entity.Status;
import com.court.digitalcourtmanagement.repository.AppUserRepository;
import com.court.digitalcourtmanagement.repository.ClientRepository;
import com.court.digitalcourtmanagement.Security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AppUserRepository appUserRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authManager,
                          JwtService jwtService,
                          UserDetailsService userDetailsService,
                          AppUserRepository appUserRepository,
                          ClientRepository clientRepository,
                          PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.appUserRepository = appUserRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        AppUser appUser = appUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails, appUser.getRole().name(), appUser.getDomainEntityId());

        return ResponseEntity.ok(new LoginResponse(
                token,
                appUser.getRole().name(),
                appUser.getUsername(),
                appUser.getDomainEntityId()
        ));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (appUserRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        Role role;
        try {
            role = Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid role. Use: ADMIN, JUDGE, LAWYER, CLIENT");
        }

        Long domainEntityId = request.getDomainEntityId();

        // CLIENT: auto-create the Client profile in the same transaction
        if (role == Role.CLIENT) {
            if (request.getFullName() == null || request.getFullName().isBlank()) {
                return ResponseEntity.badRequest().body("Full name is required for client registration");
            }

            Client client = new Client();
            client.setName(request.getFullName().trim());
            client.setCnicNumber(request.getCnicNumber());
            client.setContactNo(request.getContactNo());
            client.setEmail(request.getEmail());
            client.setStatus(Status.ACTIVE);

            Client saved = clientRepository.save(client);
            domainEntityId = saved.getId();
        }

        AppUser newUser = new AppUser(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                role,
                domainEntityId
        );
        appUserRepository.save(newUser);

        return ResponseEntity.ok(role == Role.CLIENT
                ? "Account created successfully. You can now sign in."
                : "Account registered. An admin will link your profile before you can access your dashboard.");
    }
}
