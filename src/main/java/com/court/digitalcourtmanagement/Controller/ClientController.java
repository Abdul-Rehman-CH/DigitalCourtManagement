package com.court.digitalcourtmanagement.Controller;

import com.court.digitalcourtmanagement.dto.ClientDTO;
import com.court.digitalcourtmanagement.service.ClientService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO dto) {
        return clientService.CreateClient(dto);
    }

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.GetAllClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id) {
        return clientService.GetClientById(id);
    }

    @PutMapping("/{id}")
    public ClientDTO updateClient(@PathVariable Long id,
                                  @RequestBody ClientDTO dto) {
        return clientService.UpdateClient(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.DeleteClient(id);
    }

    @GetMapping("/{id}/cases")
    public List<?> getClientCases(@PathVariable Long id) {
        return clientService.GetClientCases(id);
    }

    @PostMapping("/{id}/cnic-front")
    public void uploadFront(@PathVariable Long id,
                            @RequestParam MultipartFile file) {
        clientService.UploadCNICFront(id, file);
    }

    @PostMapping("/{id}/cnic-back")
    public void uploadBack(@PathVariable Long id,
                           @RequestParam MultipartFile file) {
        clientService.UploadCNICBack(id, file);
    }
}