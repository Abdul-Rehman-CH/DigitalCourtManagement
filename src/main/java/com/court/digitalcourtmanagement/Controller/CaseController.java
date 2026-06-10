package com.court.digitalcourtmanagement.Controller;

import com.court.digitalcourtmanagement.dto.CourtCaseDTO;
import com.court.digitalcourtmanagement.service.CourtCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController {

    private final CourtCaseService caseService;

    public CaseController(CourtCaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER', 'CLIENT')")
    public ResponseEntity<CourtCaseDTO> createCase(@RequestBody CourtCaseDTO dto) {
        return ResponseEntity.ok(caseService.createCase(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE', 'LAWYER')")
    public ResponseEntity<List<CourtCaseDTO>> getAllCases() {
        return ResponseEntity.ok(caseService.getAllCases());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE', 'LAWYER', 'CLIENT')")
    public ResponseEntity<CourtCaseDTO> getCaseById(@PathVariable Long id) {
        return ResponseEntity.ok(caseService.getCaseById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE', 'LAWYER', 'CLIENT')")
    public ResponseEntity<CourtCaseDTO> updateCase(@PathVariable Long id, @RequestBody CourtCaseDTO dto) {
        return ResponseEntity.ok(caseService.updateCase(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCase(@PathVariable Long id) {
        caseService.deleteCase(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{caseId}/judge/{judgeId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE')")
    public ResponseEntity<CourtCaseDTO> assignJudge(@PathVariable Long caseId, @PathVariable Long judgeId) {
        return ResponseEntity.ok(caseService.assignJudge(caseId, judgeId));
    }

    @PutMapping("/{caseId}/lawyer/{lawyerId}/client/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER')")
    public ResponseEntity<CourtCaseDTO> assignLawyer(@PathVariable Long caseId,
                                                      @PathVariable Long lawyerId,
                                                      @PathVariable Long clientId) {
        return ResponseEntity.ok(caseService.assignLawyer(caseId, lawyerId, clientId));
    }

    @GetMapping("/by-judge/{judgeId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE')")
    public ResponseEntity<List<CourtCaseDTO>> getCasesByJudge(@PathVariable Long judgeId) {
        return ResponseEntity.ok(caseService.getCasesByJudge(judgeId));
    }

    @GetMapping("/by-lawyer/{lawyerId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER')")
    public ResponseEntity<List<CourtCaseDTO>> getCasesByLawyer(@PathVariable Long lawyerId) {
        return ResponseEntity.ok(caseService.getCasesByLawyer(lawyerId));
    }

    @GetMapping("/by-client/{clientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER', 'JUDGE', 'CLIENT')")
    public ResponseEntity<List<CourtCaseDTO>> getCasesByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(caseService.getCasesByClient(clientId));
    }
}