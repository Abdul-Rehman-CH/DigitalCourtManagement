package com.court.digitalcourtmanagement.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.court.digitalcourtmanagement.entity.Case;
import com.court.digitalcourtmanagement.repository.CaseRepository;


@RestController
@RequestMapping("/cases")
public class CaseController {

    private final CaseRepository caseRepository;

    public CaseController(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @PostMapping
    public Case addCase(@RequestBody Case c) {
        return caseRepository.save(c);
    }

    @GetMapping
    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }
}