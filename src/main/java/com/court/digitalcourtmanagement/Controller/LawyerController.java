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

import com.court.digitalcourtmanagement.entity.Lawyer;
import com.court.digitalcourtmanagement.service.LawyerService;

@RestController
@RequestMapping("/lawyers")
public class LawyerController {

    private final LawyerService lawyerService;

    public LawyerController(LawyerService lawyerService) {
        this.lawyerService = lawyerService;
    }

    @PostMapping
    public Lawyer CreateLawyer(@RequestBody Lawyer lawyer) {
        return lawyerService.CreateLawyer(lawyer);
    }

    @GetMapping
    public List<Lawyer> GetAllLawyers() {
        return lawyerService.GetAllLawyers();
    }

    @GetMapping("/{id}")
    public Lawyer GetLawyerById(@PathVariable Long id) {
        return lawyerService.GetLawyerById(id);
    }

    @PutMapping("/{id}")
    public Lawyer UpdateLawyer(@PathVariable Long id, @RequestBody Lawyer lawyer) {
        return lawyerService.UpdateLawyer(id, lawyer);
    }

    @DeleteMapping("/{id}")
    public void DeleteLawyer(@PathVariable Long id) {
        lawyerService.DeleteLawyer(id);
    }

    @GetMapping("/{id}/cases")
    public List<?> GetAssignedCases(@PathVariable Long id) {
        return lawyerService.GetAssignedCases(id);
    }
}