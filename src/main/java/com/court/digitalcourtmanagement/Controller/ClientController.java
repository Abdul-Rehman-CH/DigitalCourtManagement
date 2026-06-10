package com.court.digitalcourtmanagement.Controller;

import com.court.digitalcourtmanagement.dto.ClientDTO;
import com.court.digitalcourtmanagement.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER')")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO dto) {
        return ResponseEntity.ok(clientService.createClient(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE', 'LAWYER')")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE', 'LAWYER', 'CLIENT')")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER')")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO dto) {
        return ResponseEntity.ok(clientService.updateClient(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/cases")
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER', 'JUDGE', 'CLIENT')")
    public ResponseEntity<List<?>> getClientCases(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientCases(id));
    }

    @PostMapping("/{id}/cnic-front")
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER')")
    public ResponseEntity<Void> uploadCNICFront(@PathVariable Long id, @RequestParam MultipartFile file) {
        clientService.uploadCNICFront(id, file);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/cnic-back")
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER')")
    public ResponseEntity<Void> uploadCNICBack(@PathVariable Long id, @RequestParam MultipartFile file) {
        clientService.uploadCNICBack(id, file);
        return ResponseEntity.ok().build();
    }
}