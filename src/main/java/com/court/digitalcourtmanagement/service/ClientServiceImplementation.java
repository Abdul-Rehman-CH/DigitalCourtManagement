package com.court.digitalcourtmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.court.digitalcourtmanagement.entity.Client;
import com.court.digitalcourtmanagement.repository.ClientRepository;
import com.court.digitalcourtmanagement.repository.CaseRepository;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CaseRepository caseRepository;

    @Override
    public Client CreateClient(Client cl) {
        return clientRepository.save(cl);
    }

    @Override
    public Client GetClientById(long cid) {
        return clientRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @Override
    public List<Client> GetAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client UpdateClient(long cid, Client cl) {
        Client existing = GetClientById(cid);

        existing.setName(cl.getName());
        existing.setEmail(cl.getEmail());
        existing.setContactNo(cl.getContactNo());
        existing.setStatus(cl.getStatus());

        return clientRepository.save(existing);
    }

    @Override
    public void DeleteClient(long cid) {
        clientRepository.deleteById(cid);
    }

    @Override
    public void UploadCNICFront(long id, MultipartFile file) {
        Client client = GetClientById(id);

        try {
            client.setCnicFrontImage(file.getBytes());
            clientRepository.save(client);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload CNIC front");
        }
    }

    @Override
    public void UploadCNICBack(long id, MultipartFile file) {
        Client client = GetClientById(id);

        try {
            client.setCnicBackImage(file.getBytes());
            clientRepository.save(client);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload CNIC back");
        }
    }
    @Override
    public List<?> GetClientCases(Long id) {
    return caseRepository.findByClient_Id(id);
}
}