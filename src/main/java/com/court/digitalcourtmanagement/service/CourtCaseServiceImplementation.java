package com.court.digitalcourtmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.digitalcourtmanagement.entity.CourtCase;
import com.court.digitalcourtmanagement.entity.Client;
import com.court.digitalcourtmanagement.entity.Judge;
import com.court.digitalcourtmanagement.entity.Lawyer;

import com.court.digitalcourtmanagement.repository.CaseRepository;
import com.court.digitalcourtmanagement.repository.ClientRepository;
import com.court.digitalcourtmanagement.repository.JudgeRepository;
import com.court.digitalcourtmanagement.repository.LawyerRepository;

import com.court.digitalcourtmanagement.service.CourtCaseService;
@Service
public class CourtCaseServiceImplementation implements CourtCaseService{
    @Autowired
    private CaseRepository caserepository;
    @Autowired
    private ClientRepository clientrepository;
    @Autowired
    private JudgeRepository judgerepository;
    @Autowired
    private LawyerRepository lawyerrepository;

    @Override
    public CourtCase CreateCase(CourtCase newcase){
        return caserepository.save(newcase);
    }

    @Override
    public CourtCase GetCaseById(Long id){
        return caserepository.findById(id)
        .orElseThrow(()-> new 
        RuntimeException("Case not found"));
    }
    @Override
    public List<CourtCase> GetAllCases(){
        return caserepository.findAll();
    }

    @Override
    public CourtCase UpdateCase(Long caseId, CourtCase updatedCase) {

        CourtCase existing = caserepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        existing.setTitle(updatedCase.getTitle());
        existing.setDescription(updatedCase.getDescription());
        existing.setStatus(updatedCase.getStatus());
        existing.setFilingDate(updatedCase.getFilingDate());

        return caserepository.save(existing);
    }
    @Override
    public void DeleteCase(Long caseId) {
        caserepository.deleteById(caseId);
    }
    @Override
    public CourtCase AssignLawyer(Long caseId, Long lawyerId, Long clientId) {

        CourtCase courtCase = caserepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        Client client = clientrepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Lawyer lawyer = lawyerrepository.findById(lawyerId)
                .orElseThrow(() -> new RuntimeException("Lawyer not found"));

        courtCase.setClient(client);

        courtCase.setLawyer(lawyer);

        return caserepository.save(courtCase);
    }
    @Override
    public CourtCase AssignJudge(Long caseId, Long judgeId) {

        CourtCase courtCase = caserepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        Judge judge = judgerepository.findById(judgeId)
                .orElseThrow(() -> new RuntimeException("Judge not found"));

        courtCase.setJudge(judge);

        return caserepository.save(courtCase);
    }
    

}
