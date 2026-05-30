package com.court.digitalcourtmanagement.service;
import org.springframework.web.multipart.MultipartFile;

import com.court.digitalcourtmanagement.entity.Client;

public interface ClientService {
    
    Client CreateClient(Client cl);

    Client GetClientByID(long cid);

    Client GetAllClients();

    void UpdateClient(long cid,Client cl);

    void DeleteClient(long cid);

    void UploadCNICfront(long id,MultipartFile file );
    
    void UploadCNICback(long id,MultipartFile file );
}
