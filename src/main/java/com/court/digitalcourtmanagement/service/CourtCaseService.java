package com.court.digitalcourtmanagement.service;

import java.util.List;

import com.court.digitalcourtmanagement.entity.CourtCase;

public interface CourtCaseService {

    CourtCase CreateCase(CourtCase newcase);

    List<CourtCase> GetAllCases();

    CourtCase GetCaseById(Long id);
    CourtCase UpdateCase(Long id, CourtCase updatedCase);
    void DeleteCase(Long id);
    CourtCase AssignLawyer(Long caseId, Long lawyerId, Long clientId);
    CourtCase AssignJudge(Long caseId, Long judgeId);
}