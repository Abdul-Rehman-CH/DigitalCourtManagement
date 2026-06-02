package com.court.digitalcourtmanagement.Controller;

import com.court.digitalcourtmanagement.dto.JudgeDTO;
import com.court.digitalcourtmanagement.service.JudgeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/judges")
public class JudgeController {

    private final JudgeService judgeService;

    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @PostMapping
    public JudgeDTO addJudge(@RequestBody JudgeDTO dto) {
        return judgeService.CreateJudge(dto);
    }

    @GetMapping
    public List<JudgeDTO> getAllJudges() {
        return judgeService.GetAllJudges();
    }

    @GetMapping("/{id}")
    public JudgeDTO getJudgeById(@PathVariable Long id) {
        return judgeService.GetJudgeById(id);
    }

    @PutMapping("/{id}")
    public JudgeDTO updateJudge(@PathVariable Long id,
                               @RequestBody JudgeDTO dto) {
        return judgeService.UpdateJudge(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteJudge(@PathVariable Long id) {
        judgeService.DeleteJudge(id);
    }
}