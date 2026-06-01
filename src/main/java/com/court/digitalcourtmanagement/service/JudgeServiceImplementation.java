package com.court.digitalcourtmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.court.digitalcourtmanagement.entity.Judge;
import com.court.digitalcourtmanagement.repository.CaseRepository;
import com.court.digitalcourtmanagement.repository.JudgeRepository;

@Service
public class JudgeServiceImplementation implements JudgeService {

    @Autowired
    private JudgeRepository judgeRepository;

    @Autowired
    private CaseRepository caseRepository;


    @Override
    public Judge CreateJudge(Judge j) {
        return judgeRepository.save(j);
    }

    @Override
    public Judge GetJudgeById(long jid) {
        return judgeRepository.findById(jid)
                .orElseThrow(() -> new RuntimeException("Judge not found"));
    }

    @Override
    public List<Judge> GetAllJudges() {
        return judgeRepository.findAll();
    }

    @Override
    public Judge UpdateJudge(long jid, Judge judge) {
        Judge existing = GetJudgeById(jid);

        existing.setName(judge.getName());
        existing.setEmail(judge.getEmail());
        existing.setContactNo(judge.getContactNo());
        existing.setStatus(judge.getStatus());

        return judgeRepository.save(existing);
    }

    @Override
    public void DeleteJudge(long jid) {
        judgeRepository.deleteById(jid);
    }

    @Override
    public List<?> GetAssignedCases(Long judgeId) {
        Judge judge = GetJudgeById(judgeId);

        return caseRepository.findAll()
                .stream()
                .filter(c -> c.getJudge() != null
                        && c.getJudge().getId().equals(judge.getId()))
                .toList();
    }
}