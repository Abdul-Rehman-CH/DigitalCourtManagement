package com.court.digitalcourtmanagement.Mapper;

import com.court.digitalcourtmanagement.dto.CourtCaseDTO;
import com.court.digitalcourtmanagement.entity.CourtCase;

public class CourtCaseMapper {

    public static CourtCaseDTO toDTO(CourtCase c) {

        if (c == null) return null;

        CourtCaseDTO dto = new CourtCaseDTO();

        dto.setCaseId(c.getCaseId());
        dto.setTitle(c.getTitle());
        dto.setDescription(c.getDescription());
        dto.setStatus(c.getStatus());
        dto.setFilingDate(c.getFilingDate());

        if (c.getClient() != null) {
            dto.setClientId(c.getClient().getId());
            dto.setClientName(c.getClient().getName());
            dto.setClientCnic(c.getClient().getCnicNumber());
        }

        if (c.getJudge() != null) {
            dto.setJudgeId(c.getJudge().getId());
            dto.setJudgeName(c.getJudge().getName());
        }

        if (c.getLawyer() != null) {
            dto.setLawyerId(c.getLawyer().getId());
            dto.setLawyerName(c.getLawyer().getName());
        }

        return dto;
    }
}