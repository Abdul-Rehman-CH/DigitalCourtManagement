package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.CourtCaseDTO;

import java.util.List;

public interface CourtCaseService {

    CourtCaseDTO CreateCase(CourtCaseDTO newCase);

    List<CourtCaseDTO> GetAllCases();

    CourtCaseDTO GetCaseById(Long id);

    CourtCaseDTO UpdateCase(Long id, CourtCaseDTO updatedCase);

    void DeleteCase(Long id);

    CourtCaseDTO AssignLawyer(Long caseId, Long lawyerId, Long clientId);

    CourtCaseDTO AssignJudge(Long caseId, Long judgeId);
}