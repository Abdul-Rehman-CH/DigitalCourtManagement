package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.ClientDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientService {

    ClientDTO CreateClient(ClientDTO cl);

    ClientDTO GetClientById(long cid);

    List<ClientDTO> GetAllClients();

    ClientDTO UpdateClient(long cid, ClientDTO cl);

    void DeleteClient(long cid);

    void UploadCNICFront(long id, MultipartFile file);

    void UploadCNICBack(long id, MultipartFile file);

    List<?> GetClientCases(Long id);
}