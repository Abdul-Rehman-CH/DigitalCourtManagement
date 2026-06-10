package com.court.digitalcourtmanagement.config;

import com.court.digitalcourtmanagement.entity.AppUser;
import com.court.digitalcourtmanagement.entity.Role;
import com.court.digitalcourtmanagement.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdminUser(AppUserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            if (!userRepo.existsByUsername("admin")) {
                AppUser admin = new AppUser("admin", encoder.encode("admin123"), Role.ADMIN, null);
                userRepo.save(admin);
                System.out.println("✅ Default admin created — username: admin, password: admin123");
            }
        };
    }
}