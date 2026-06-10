package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.JudgeDTO;
import com.court.digitalcourtmanagement.entity.Judge;
import com.court.digitalcourtmanagement.entity.Status;
import com.court.digitalcourtmanagement.Mapper.mappers;
import com.court.digitalcourtmanagement.repository.CaseRepository;
import com.court.digitalcourtmanagement.repository.JudgeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JudgeServiceImpl implements JudgeService {

    private final JudgeRepository judgeRepository;
    private final CaseRepository caseRepository;

    public JudgeServiceImpl(JudgeRepository judgeRepository, CaseRepository caseRepository) {
        this.judgeRepository = judgeRepository;
        this.caseRepository = caseRepository;
    }

    @Override
    public JudgeDTO createJudge(JudgeDTO dto) {
        Judge j = new Judge();
        j.setName(dto.getName());
        j.setEmail(dto.getEmail());
        j.setContactNo(dto.getContactNo());
        j.setCourtRoom(dto.getCourtRoom());
        if (dto.getStatus() != null) j.setStatus(Status.valueOf(dto.getStatus()));
        else j.setStatus(Status.ACTIVE);
        return mappers.toDTO(judgeRepository.save(j));
    }

    @Override
    @Transactional(readOnly = true)
    public JudgeDTO getJudgeById(Long id) {
        return mappers.toDTO(judgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Judge not found with id: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<JudgeDTO> getAllJudges() {
        return judgeRepository.findAll().stream().map(mappers::toDTO).collect(Collectors.toList());
    }

    @Override
    public JudgeDTO updateJudge(Long id, JudgeDTO dto) {
        Judge j = judgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Judge not found with id: " + id));
        j.setName(dto.getName());
        j.setEmail(dto.getEmail());
        j.setContactNo(dto.getContactNo());
        j.setCourtRoom(dto.getCourtRoom());
        if (dto.getStatus() != null) j.setStatus(Status.valueOf(dto.getStatus()));
        return mappers.toDTO(judgeRepository.save(j));
    }

    @Override
    public void deleteJudge(Long id) {
        judgeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<?> getJudgeCases(Long id) {
        return caseRepository.findByJudgeAssigned_Id(id).stream()
                .map(mappers::toDTO).collect(Collectors.toList());
    }
}