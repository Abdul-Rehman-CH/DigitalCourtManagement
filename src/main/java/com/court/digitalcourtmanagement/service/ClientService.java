package com.court.digitalcourtmanagement.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.court.digitalcourtmanagement.entity.Client;

public interface ClientService {

    Client CreateClient(Client cl);

    Client GetClientById(long cid);

    List<Client> GetAllClients();

    Client UpdateClient(long cid, Client cl);

    void DeleteClient(long cid);

    void UploadCNICFront(long id, MultipartFile file);

    void UploadCNICBack(long id, MultipartFile file);
    List<?> GetClientCases(Long id);
}