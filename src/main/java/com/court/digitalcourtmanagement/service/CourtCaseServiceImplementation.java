package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.CourtCaseDTO;
import com.court.digitalcourtmanagement.entity.*;
import com.court.digitalcourtmanagement.Mapper.CourtCaseMapper;
import com.court.digitalcourtmanagement.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtCaseServiceImplementation implements CourtCaseService {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JudgeRepository judgeRepository;

    @Autowired
    private LawyerRepository lawyerRepository;

    @Override
    public CourtCaseDTO CreateCase(CourtCaseDTO dto) {

        if (dto.getClientId() == null) {
            throw new RuntimeException("Client ID required");
        }

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        CourtCase c = new CourtCase();
        c.setTitle(dto.getTitle());
        c.setDescription(dto.getDescription());
        c.setStatus(dto.getStatus());
        c.setFilingDate(dto.getFilingDate());
        c.setClient(client);

        return CourtCaseMapper.toDTO(caseRepository.save(c));
    }

    @Override
    public List<CourtCaseDTO> GetAllCases() {
        return caseRepository.findAll()
                .stream()
                .map(CourtCaseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourtCaseDTO GetCaseById(Long id) {
        return CourtCaseMapper.toDTO(
                caseRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Case not found"))
        );
    }

    @Override
    public CourtCaseDTO UpdateCase(Long id, CourtCaseDTO dto) {

        CourtCase existing = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setStatus(dto.getStatus());
        existing.setFilingDate(dto.getFilingDate());

        return CourtCaseMapper.toDTO(caseRepository.save(existing));
    }

    @Override
    public void DeleteCase(Long id) {
        caseRepository.deleteById(id);
    }

    @Override
    public CourtCaseDTO AssignLawyer(Long caseId, Long lawyerId, Long clientId) {

        CourtCase c = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        Lawyer l = lawyerRepository.findById(lawyerId)
                .orElseThrow(() -> new RuntimeException("Lawyer not found"));

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        c.setLawyer(l);
        c.setClient(client);

        return CourtCaseMapper.toDTO(caseRepository.save(c));
    }

    @Override
    public CourtCaseDTO AssignJudge(Long caseId, Long judgeId) {

        CourtCase c = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        Judge j = judgeRepository.findById(judgeId)
                .orElseThrow(() -> new RuntimeException("Judge not found"));

        c.setJudge(j);

        return CourtCaseMapper.toDTO(caseRepository.save(c));
    }
}