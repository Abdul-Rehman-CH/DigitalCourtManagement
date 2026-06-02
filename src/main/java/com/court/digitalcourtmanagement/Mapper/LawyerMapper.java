package com.court.digitalcourtmanagement.Mapper;

import com.court.digitalcourtmanagement.dto.LawyerDTO;
import com.court.digitalcourtmanagement.entity.Lawyer;

import java.util.stream.Collectors;

public class LawyerMapper {

    public static LawyerDTO toDTO(Lawyer l) {

        if (l == null) return null;

        LawyerDTO dto = new LawyerDTO();

        dto.setId(l.getId());
        dto.setName(l.getName());
        dto.setEmail(l.getEmail());
        dto.setContactNo(l.getContactNo());
        dto.setStatus(l.getStatus() != null ? l.getStatus().name() : null);

        if (l.getCases() != null) {
            dto.setCaseIds(
                l.getCases()
                 .stream()
                 .map(c -> c.getCaseId())
                 .collect(Collectors.toList())
            );
        }

        return dto;
    }
}