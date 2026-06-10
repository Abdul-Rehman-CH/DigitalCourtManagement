package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.CourtCaseDTO;
import com.court.digitalcourtmanagement.entity.*;
import com.court.digitalcourtmanagement.Mapper.mappers;
import com.court.digitalcourtmanagement.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourtCaseServiceImpl implements CourtCaseService {

    private final CaseRepository caseRepository;
    private final ClientRepository clientRepository;
    private final JudgeRepository judgeRepository;
    private final LawyerRepository lawyerRepository;

    public CourtCaseServiceImpl(CaseRepository caseRepository,
                                ClientRepository clientRepository,
                                JudgeRepository judgeRepository,
                                LawyerRepository lawyerRepository) {
        this.caseRepository = caseRepository;
        this.clientRepository = clientRepository;
        this.judgeRepository = judgeRepository;
        this.lawyerRepository = lawyerRepository;
    }

    @Override
    public CourtCaseDTO createCase(CourtCaseDTO dto) {
        if (dto.getClientId() == null) throw new RuntimeException("Client ID is required");

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        CourtCase c = new CourtCase();
        c.setTitle(dto.getTitle());
        c.setDescription(dto.getDescription());
        c.setStatus(dto.getStatus() != null ? dto.getStatus() : "PENDING");
        c.setFilingDate(dto.getFilingDate());
        c.setClient(client);

        // Auto-assign the judge with the fewest active cases
        List<Judge> available = judgeRepository.findActiveJudgesOrderedByActiveCaseload();
        if (available.isEmpty()) {
            throw new RuntimeException("No active judges are available to be assigned. Please add an active judge first.");
        }
        c.setJudge(available.get(0));

        // Lawyer is chosen by the client (optional at filing time)
        if (dto.getLawyerId() != null) {
            Lawyer lawyer = lawyerRepository.findById(dto.getLawyerId())
                    .orElseThrow(() -> new RuntimeException("Lawyer not found"));
            c.setLawyer(lawyer);
        }

        return mappers.toDTO(caseRepository.save(c));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourtCaseDTO> getAllCases() {
        return caseRepository.findAll().stream().map(mappers::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CourtCaseDTO getCaseById(Long id) {
        return mappers.toDTO(caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found with id: " + id)));
    }

    @Override
    public CourtCaseDTO updateCase(Long id, CourtCaseDTO dto) {
        CourtCase c = caseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found with id: " + id));
        c.setTitle(dto.getTitle());
        c.setDescription(dto.getDescription());
        c.setStatus(dto.getStatus());
        c.setFilingDate(dto.getFilingDate());

        // Allow updating lawyer assignment (client can change their lawyer)
        if (dto.getLawyerId() != null) {
            Lawyer lawyer = lawyerRepository.findById(dto.getLawyerId())
                    .orElseThrow(() -> new RuntimeException("Lawyer not found"));
            c.setLawyer(lawyer);
        }

        // Judge is never changed via update — auto-assigned at creation only
        return mappers.toDTO(caseRepository.save(c));
    }

    @Override
    public void deleteCase(Long id) {
        caseRepository.deleteById(id);
    }

    @Override
    public CourtCaseDTO assignJudge(Long caseId, Long judgeId) {
        CourtCase c = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));
        Judge j = judgeRepository.findById(judgeId)
                .orElseThrow(() -> new RuntimeException("Judge not found"));
        c.setJudge(j);
        return mappers.toDTO(caseRepository.save(c));
    }

    @Override
    public CourtCaseDTO assignLawyer(Long caseId, Long lawyerId, Long clientId) {
        CourtCase c = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));
        Lawyer l = lawyerRepository.findById(lawyerId)
                .orElseThrow(() -> new RuntimeException("Lawyer not found"));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        c.setLawyer(l);
        c.setClient(client);
        return mappers.toDTO(caseRepository.save(c));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourtCaseDTO> getCasesByJudge(Long judgeId) {
        return caseRepository.findByJudgeAssigned_Id(judgeId).stream()
                .map(mappers::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourtCaseDTO> getCasesByLawyer(Long lawyerId) {
        return caseRepository.findByLawyerAssigned_Id(lawyerId).stream()
                .map(mappers::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourtCaseDTO> getCasesByClient(Long clientId) {
        return caseRepository.findByClient_Id(clientId).stream()
                .map(mappers::toDTO).collect(Collectors.toList());
    }
}
