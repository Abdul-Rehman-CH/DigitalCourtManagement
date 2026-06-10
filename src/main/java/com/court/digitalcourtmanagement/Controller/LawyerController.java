package com.court.digitalcourtmanagement.Controller;

import com.court.digitalcourtmanagement.dto.LawyerDTO;
import com.court.digitalcourtmanagement.service.LawyerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lawyers")
// FIX: original LawyerController returned raw Lawyer entity (not DTO) — now uses LawyerDTO
public class LawyerController {

    private final LawyerService lawyerService;

    public LawyerController(LawyerService lawyerService) {
        this.lawyerService = lawyerService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LawyerDTO> createLawyer(@RequestBody LawyerDTO dto) {
        return ResponseEntity.ok(lawyerService.createLawyer(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE', 'CLIENT')")
    public ResponseEntity<List<LawyerDTO>> getAllLawyers() {
        return ResponseEntity.ok(lawyerService.getAllLawyers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE', 'LAWYER', 'CLIENT')")
    public ResponseEntity<LawyerDTO> getLawyerById(@PathVariable Long id) {
        return ResponseEntity.ok(lawyerService.getLawyerById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER')")
    public ResponseEntity<LawyerDTO> updateLawyer(@PathVariable Long id, @RequestBody LawyerDTO dto) {
        return ResponseEntity.ok(lawyerService.updateLawyer(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLawyer(@PathVariable Long id) {
        lawyerService.deleteLawyer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/cases")
    @PreAuthorize("hasAnyRole('ADMIN', 'LAWYER', 'JUDGE')")
    public ResponseEntity<List<?>> getAssignedCases(@PathVariable Long id) {
        return ResponseEntity.ok(lawyerService.getAssignedCases(id));
    }
}