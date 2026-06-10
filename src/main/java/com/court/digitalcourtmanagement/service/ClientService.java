package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.ClientDTO;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ClientService {
    ClientDTO createClient(ClientDTO dto);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(Long id, ClientDTO dto);
    void deleteClient(Long id);
    void uploadCNICFront(Long id, MultipartFile file);
    void uploadCNICBack(Long id, MultipartFile file);
    List<?> getClientCases(Long id);
}