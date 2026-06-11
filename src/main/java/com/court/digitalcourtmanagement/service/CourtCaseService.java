package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.CourtCaseDTO;
import java.util.List;

public interface CourtCaseService {

    CourtCaseDTO createCase(CourtCaseDTO dto);

    List<CourtCaseDTO> getAllCases();

    CourtCaseDTO getCaseById(Long id);

    CourtCaseDTO updateCase(Long id, CourtCaseDTO dto);

    void deleteCase(Long id);

    CourtCaseDTO assignJudge(Long caseId, Long judgeId);

    CourtCaseDTO assignLawyer(Long caseId, Long lawyerId, Long clientId);

    List<CourtCaseDTO> getCasesByJudge(Long judgeId);

    List<CourtCaseDTO> getCasesByLawyer(Long lawyerId);

    CourtCaseDTO updateHearingStatus(Long id, String status, String remarks);

    CourtCaseDTO updateHearingSchedule(Long id, String hearingDate, String remarks);

    List<CourtCaseDTO> getCasesByClient(Long clientId);
}
