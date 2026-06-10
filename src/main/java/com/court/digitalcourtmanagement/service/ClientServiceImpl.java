package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.ClientDTO;
import com.court.digitalcourtmanagement.entity.Client;
import com.court.digitalcourtmanagement.entity.Status;
import com.court.digitalcourtmanagement.Mapper.mappers;
import com.court.digitalcourtmanagement.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO createClient(ClientDTO dto) {
        Client c = new Client();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setContactNo(dto.getContactNo());
        c.setCnicNumber(dto.getCnicNumber());
        if (dto.getStatus() != null) c.setStatus(Status.valueOf(dto.getStatus()));
        else c.setStatus(Status.ACTIVE);
        return mappers.toDTO(clientRepository.save(c));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDTO getClientById(Long id) {
        return mappers.toDTO(clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(mappers::toDTO).collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        Client c = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setContactNo(dto.getContactNo());
        c.setCnicNumber(dto.getCnicNumber());
        if (dto.getStatus() != null) c.setStatus(Status.valueOf(dto.getStatus()));
        return mappers.toDTO(clientRepository.save(c));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void uploadCNICFront(Long id, MultipartFile file) {
        Client c = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        try {
            c.setCnicFrontImage(file.getBytes());
            clientRepository.save(c);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload CNIC front image: " + e.getMessage());
        }
    }

    @Override
    public void uploadCNICBack(Long id, MultipartFile file) {
        Client c = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        try {
            c.setCnicBackImage(file.getBytes());
            clientRepository.save(c);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload CNIC back image: " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<?> getClientCases(Long id) {
        Client c = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return c.getCases().stream().map(mappers::toDTO).collect(Collectors.toList());
    }
}