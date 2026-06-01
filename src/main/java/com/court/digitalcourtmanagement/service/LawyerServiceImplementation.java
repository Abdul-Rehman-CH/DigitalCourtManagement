package com.court.digitalcourtmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.digitalcourtmanagement.entity.Lawyer;
import com.court.digitalcourtmanagement.repository.LawyerRepository;
import com.court.digitalcourtmanagement.repository.CaseRepository;

@Service
public class LawyerServiceImplementation implements LawyerService {

    @Autowired
    private LawyerRepository lawyerRepository;

    @Autowired
    private CaseRepository caseRepository;


    @Override
    public Lawyer CreateLawyer(Lawyer lawyer) {
        return lawyerRepository.save(lawyer);
    }

    @Override
    public Lawyer GetLawyerById(Long id) {
        return lawyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lawyer not found"));
    }

    @Override
    public List<Lawyer> GetAllLawyers() {
        return lawyerRepository.findAll();
    }

    @Override
    public Lawyer UpdateLawyer(Long id, Lawyer updatedLawyer) {
        Lawyer existing = GetLawyerById(id);

        existing.setName(updatedLawyer.getName());
        existing.setEmail(updatedLawyer.getEmail());
        existing.setContactNo(updatedLawyer.getContactNo());
        existing.setStatus(updatedLawyer.getStatus());
        existing.setSpec(updatedLawyer.getSpec());
        existing.setBarCNum(updatedLawyer.getBarCNum());

        return lawyerRepository.save(existing);
    }


    @Override
    public void DeleteLawyer(Long id) {
        lawyerRepository.deleteById(id);
    }

    @Override
    public List<?> GetAssignedCases(Long id) {
        Lawyer lawyer = GetLawyerById(id);

        return caseRepository.findAll()
                .stream()
                .filter(c -> c.getLawyer() != null
                        && c.getLawyer().getId() == lawyer.getId())
                .toList();
    }
}