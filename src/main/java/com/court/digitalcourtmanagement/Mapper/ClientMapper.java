package com.court.digitalcourtmanagement.Mapper;

import com.court.digitalcourtmanagement.dto.ClientDTO;
import com.court.digitalcourtmanagement.entity.Client;

import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientDTO toDTO(Client c) {

        if (c == null) return null;

        ClientDTO dto = new ClientDTO();

        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setEmail(c.getEmail());
        dto.setContactNo(c.getContactNo());
        dto.setCnicNumber(c.getCnicNumber());
        dto.setStatus(c.getStatus() != null ? c.getStatus().name() : null);

        if (c.getCases() != null) {
            dto.setCaseIds(
                c.getCases()
                 .stream()
                 .map(x -> x.getCaseId())
                 .collect(Collectors.toList())
            );
        }

        return dto;
    }
}