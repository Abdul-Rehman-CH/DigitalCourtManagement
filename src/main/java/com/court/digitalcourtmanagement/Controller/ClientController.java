package com.court.digitalcourtmanagement.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.court.digitalcourtmanagement.entity.Client;
import com.court.digitalcourtmanagement.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client CreateClient(@RequestBody Client client) {
        return clientService.CreateClient(client);
    }

    @GetMapping
    public List<Client> GetAllClients() {
        return clientService.GetAllClients();
    }

    @GetMapping("/{id}")
    public Client GetClientById(@PathVariable Long id) {
        return clientService.GetClientById(id);
    }

    @PutMapping("/{id}")
    public Client UpdateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.UpdateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void DeleteClient(@PathVariable Long id) {
        clientService.DeleteClient(id);
    }

    @GetMapping("/{id}/cases")
    public List<?> GetClientCases(@PathVariable Long id) {
        return clientService.GetClientCases(id);
    }
    @PostMapping("/{id}/cnic-back")
    public void uploadBack(@PathVariable Long id, @RequestParam MultipartFile file) {
    clientService.UploadCNICBack(id, file);
    }
    @PostMapping("/{id}/cnic-front")
    public void uploadFront(@PathVariable Long id, @RequestParam MultipartFile file) {
    clientService.UploadCNICFront(id, file);
    }
}