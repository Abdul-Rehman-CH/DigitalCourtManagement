package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.ClientDTO;
import com.court.digitalcourtmanagement.entity.Client;
import com.court.digitalcourtmanagement.Mapper.ClientMapper;
import com.court.digitalcourtmanagement.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDTO CreateClient(ClientDTO dto) {

        Client c = new Client();

        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setContactNo(dto.getContactNo());
        c.setCnicNumber(dto.getCnicNumber());

        return ClientMapper.toDTO(clientRepository.save(c));
    }

    @Override
    public ClientDTO GetClientById(long cid) {

        Client c = clientRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return ClientMapper.toDTO(c);
    }

    @Override
    public List<ClientDTO> GetAllClients() {

        return clientRepository.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO UpdateClient(long cid, ClientDTO dto) {

        Client c = clientRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setContactNo(dto.getContactNo());
        c.setCnicNumber(dto.getCnicNumber());

        return ClientMapper.toDTO(clientRepository.save(c));
    }

    @Override
    public void DeleteClient(long cid) {
        clientRepository.deleteById(cid);
    }

    @Override
    public void UploadCNICFront(long id, MultipartFile file) {
        Client c = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        try {
            c.setCnicFrontImage(file.getBytes());
            clientRepository.save(c);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void UploadCNICBack(long id, MultipartFile file) {
        Client c = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        try {
            c.setCnicBackImage(file.getBytes());
            clientRepository.save(c);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<?> GetClientCases(Long id) {

        Client c = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return c.getCases()
                .stream()
                .map(com.court.digitalcourtmanagement.Mapper.CourtCaseMapper::toDTO)
                .collect(Collectors.toList());
    }
}