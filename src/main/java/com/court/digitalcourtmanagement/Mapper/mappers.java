package com.court.digitalcourtmanagement.Mapper;

import com.court.digitalcourtmanagement.dto.*;
import com.court.digitalcourtmanagement.entity.*;

import java.util.stream.Collectors;


public class mappers {

    public static JudgeDTO toDTO(Judge j) {
        if (j == null) return null;
        JudgeDTO dto = new JudgeDTO();
        dto.setId(j.getId());
        dto.setName(j.getName());
        dto.setEmail(j.getEmail());
        dto.setContactNo(j.getContactNo());
        dto.setStatus(j.getStatus() != null ? j.getStatus().name() : null);
        dto.setCourtRoom(j.getCourtRoom());
        if (j.getCases() != null) {
            dto.setCaseIds(j.getCases().stream().map(CourtCase::getCaseId).collect(Collectors.toList()));
        }
        return dto;
    }

    public static LawyerDTO toDTO(Lawyer l) {
        if (l == null) return null;
        LawyerDTO dto = new LawyerDTO();
        dto.setId(l.getId());
        dto.setName(l.getName());
        dto.setEmail(l.getEmail());
        dto.setContactNo(l.getContactNo());
        dto.setStatus(l.getStatus() != null ? l.getStatus().name() : null);
        dto.setBarNumber(l.getBarNumber());
        dto.setSpecialization(l.getSpecialization());
        if (l.getCases() != null) {
            dto.setCaseIds(l.getCases().stream().map(CourtCase::getCaseId).collect(Collectors.toList()));
        }
        return dto;
    }

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
            dto.setCaseIds(c.getCases().stream().map(CourtCase::getCaseId).collect(Collectors.toList()));
        }
        return dto;
    }

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