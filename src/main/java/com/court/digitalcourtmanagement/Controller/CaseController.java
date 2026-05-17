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
import com.court.digitalcourtmanagement.repository.CaseRepository;

@RestController
@RequestMapping("/cases")
public class CaseController {

    private final CaseRepository caseRepository;

    public CaseController(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @PostMapping
    public CourtCase addCase(@RequestBody CourtCase c) {
        return caseRepository.save(c);
    }

    @GetMapping
    public List<CourtCase> getAllCases() {
        return caseRepository.findAll();
    }

    @GetMapping("/{id}")
    public CourtCase getCaseById(@PathVariable Long id) {
        return caseRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteCase(@PathVariable Long id) {
        caseRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public CourtCase updateCase(@PathVariable Long id, @RequestBody CourtCase updatedCase) {

        return caseRepository.findById(id).map(c -> {

            c.setTitle(updatedCase.getTitle());
            c.setDescription(updatedCase.getDescription());
            c.setStatus(updatedCase.getStatus());
            c.setFilingDate(updatedCase.getFilingDate());

            return caseRepository.save(c);

        }).orElse(null);
    }
}
