package com.court.digitalcourtmanagement.service;

import java.util.List;

import com.court.digitalcourtmanagement.entity.CourtCase;

public interface CourtCaseService {

    CourtCase createCase(CourtCase courtCase);

    List<CourtCase> getAllCases();

    CourtCase getCaseById(Long id);

    CourtCase assignJudgeToCase(Long caseId, Long judgeId);
}