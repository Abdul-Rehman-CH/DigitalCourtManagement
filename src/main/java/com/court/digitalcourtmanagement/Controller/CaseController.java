package com.court.digitalcourtmanagement.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.court.digitalcourtmanagement.entity.CourtCase;
import com.court.digitalcourtmanagement.service.CourtCaseService;

@RestController
@RequestMapping("/cases")
public class CaseController {

    private final CourtCaseService courtCaseService;

    public CaseController(CourtCaseService courtCaseService) {
        this.courtCaseService = courtCaseService;
    }

    @PostMapping
    public CourtCase addCase(@RequestBody CourtCase c) {
        return courtCaseService.CreateCase(c);
    }

    @GetMapping
    public List<CourtCase> GetAllCases() {
        return courtCaseService.GetAllCases();
    }

    @GetMapping("/{id}")
    public CourtCase GetCaseById(@PathVariable Long id) {
        return courtCaseService.GetCaseById(id);
    }

    @DeleteMapping("/{id}")
    public void DeleteCase(@PathVariable Long id) {
        courtCaseService.DeleteCase(id);
    }

    @PutMapping("/{id}")
    public CourtCase UpdateCase(@PathVariable Long id,
                                @RequestBody CourtCase updatedCase) {

        return courtCaseService.UpdateCase(id, updatedCase);
    }

    @PutMapping("/{caseId}/judge/{judgeId}")
    public CourtCase AssignJudge(@PathVariable Long caseId,@PathVariable Long judgeId) {

        return courtCaseService.AssignJudge(caseId, judgeId);
    }

@PutMapping("/{caseId}/lawyer/{lawyerId}/client/{clientId}")
public CourtCase AssignLawyer(@PathVariable Long caseId,@PathVariable Long lawyerId,@PathVariable Long clientId) {

    return courtCaseService.AssignLawyer(caseId, lawyerId, clientId);
}
}