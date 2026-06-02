package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.JudgeDTO;
import com.court.digitalcourtmanagement.entity.Judge;
import com.court.digitalcourtmanagement.entity.Status;
import com.court.digitalcourtmanagement.Mapper.JudgeMapper;
import com.court.digitalcourtmanagement.repository.JudgeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImplementation implements JudgeService {

    @Autowired
    private JudgeRepository judgeRepository;

    @Override
    public JudgeDTO CreateJudge(JudgeDTO dto) {

        Judge j = new Judge();

        j.setName(dto.getName());
        j.setEmail(dto.getEmail());
        j.setContactNo(dto.getContactNo());

        if (dto.getStatus() != null) {
            j.setStatus(Status.valueOf(dto.getStatus()));
        }

        return JudgeMapper.toDTO(judgeRepository.save(j));
    }

    @Override
    public JudgeDTO GetJudgeById(Long id) {

        Judge j = judgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Judge not found"));

        return JudgeMapper.toDTO(j);
    }

    @Override
    public List<JudgeDTO> GetAllJudges() {

        return judgeRepository.findAll()
                .stream()
                .map(JudgeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JudgeDTO UpdateJudge(Long id, JudgeDTO dto) {

        Judge existing = judgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Judge not found"));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setContactNo(dto.getContactNo());

        if (dto.getStatus() != null) {
            existing.setStatus(Status.valueOf(dto.getStatus()));
        }

        return JudgeMapper.toDTO(judgeRepository.save(existing));
    }

    @Override
    public void DeleteJudge(Long id) {
        judgeRepository.deleteById(id);
    }
}