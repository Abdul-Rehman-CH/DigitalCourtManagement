package com.court.digitalcourtmanagement.Controller;

import com.court.digitalcourtmanagement.dto.JudgeDTO;
import com.court.digitalcourtmanagement.service.JudgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/judges")
public class JudgeController {

    private final JudgeService judgeService;

    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JudgeDTO> createJudge(@RequestBody JudgeDTO dto) {
        return ResponseEntity.ok(judgeService.createJudge(dto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE', 'LAWYER')")
    public ResponseEntity<List<JudgeDTO>> getAllJudges() {
        return ResponseEntity.ok(judgeService.getAllJudges());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE', 'LAWYER', 'CLIENT')")
    public ResponseEntity<JudgeDTO> getJudgeById(@PathVariable Long id) {
        return ResponseEntity.ok(judgeService.getJudgeById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JudgeDTO> updateJudge(@PathVariable Long id, @RequestBody JudgeDTO dto) {
        return ResponseEntity.ok(judgeService.updateJudge(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteJudge(@PathVariable Long id) {
        judgeService.deleteJudge(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/cases")
    @PreAuthorize("hasAnyRole('ADMIN', 'JUDGE')")
    public ResponseEntity<List<?>> getJudgeCases(@PathVariable Long id) {
        return ResponseEntity.ok(judgeService.getJudgeCases(id));
    }
}
