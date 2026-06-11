package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.JudgeDTO;
import java.util.List;

public interface JudgeService {

    JudgeDTO createJudge(JudgeDTO dto);

    JudgeDTO getJudgeById(Long id);

    List<JudgeDTO> getAllJudges();

    JudgeDTO updateJudge(Long id, JudgeDTO dto);

    void deleteJudge(Long id);

    List<?> getJudgeCases(Long id);
}
