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
import com.court.digitalcourtmanagement.entity.Judge;
import com.court.digitalcourtmanagement.repository.JudgeRepository;

@RestController
@RequestMapping("/judges")
public class JudgeController {
    private final JudgeRepository judgeRepository;
    public JudgeController(JudgeRepository judgeRepository){
        this.judgeRepository=judgeRepository;
    }
    @PostMapping
    public Judge addJudge(@RequestBody Judge j ){
        return judgeRepository.save(j);
    }
        @GetMapping
    public List<Judge> getAllJudges() {
        return judgeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Judge getJudgeById(@PathVariable Long id) {
        return judgeRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteJudge(@PathVariable Long id) {
        judgeRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public Judge updateJudge(@PathVariable Long id, @RequestBody Judge updatedJudge) {

        return judgeRepository.findById(id).map(j -> {

            j.setJudgeName(updatedJudge.getJudgeName());
            return judgeRepository.save(j);

        }).orElse(null);
    }
}
