package com.court.digitalcourtmanagement.Mapper;

import com.court.digitalcourtmanagement.dto.JudgeDTO;
import com.court.digitalcourtmanagement.entity.Judge;

import java.util.stream.Collectors;

public class JudgeMapper {

    public static JudgeDTO toDTO(Judge j) {

        if (j == null) return null;

        JudgeDTO dto = new JudgeDTO();

        dto.setId(j.getId());
        dto.setName(j.getName());
        dto.setEmail(j.getEmail());
        dto.setContactNo(j.getContactNo());
        dto.setStatus(j.getStatus() != null ? j.getStatus().name() : null);

        if (j.getCases() != null) {
            dto.setCaseIds(
                j.getCases()
                 .stream()
                 .map(c -> c.getCaseId())
                 .collect(Collectors.toList())
            );
        }

        return dto;
    }
}