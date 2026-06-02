package com.court.digitalcourtmanagement.Controller;

import com.court.digitalcourtmanagement.dto.CourtCaseDTO;
import com.court.digitalcourtmanagement.service.CourtCaseService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cases")
public class CaseController {

    private final CourtCaseService courtCaseService;

    public CaseController(CourtCaseService courtCaseService) {
        this.courtCaseService = courtCaseService;
    }

    @PostMapping
    public CourtCaseDTO addCase(@RequestBody CourtCaseDTO dto) {
        return courtCaseService.CreateCase(dto);
    }

    @GetMapping
    public List<CourtCaseDTO> getAllCases() {
        return courtCaseService.GetAllCases();
    }

    @GetMapping("/{id}")
    public CourtCaseDTO getCaseById(@PathVariable Long id) {
        return courtCaseService.GetCaseById(id);
    }

    @PutMapping("/{id}")
    public CourtCaseDTO updateCase(@PathVariable Long id,
                                    @RequestBody CourtCaseDTO dto) {
        return courtCaseService.UpdateCase(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCase(@PathVariable Long id) {
        courtCaseService.DeleteCase(id);
    }

    @PutMapping("/{caseId}/judge/{judgeId}")
    public CourtCaseDTO assignJudge(@PathVariable Long caseId,
                                    @PathVariable Long judgeId) {
        return courtCaseService.AssignJudge(caseId, judgeId);
    }

    @PutMapping("/{caseId}/lawyer/{lawyerId}/client/{clientId}")
    public CourtCaseDTO assignLawyer(@PathVariable Long caseId,
                                     @PathVariable Long lawyerId,
                                     @PathVariable Long clientId) {
        return courtCaseService.AssignLawyer(caseId, lawyerId, clientId);
    }
}