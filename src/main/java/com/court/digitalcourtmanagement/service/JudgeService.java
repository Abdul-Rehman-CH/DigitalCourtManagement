package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.JudgeDTO;

import java.util.List;

public interface JudgeService {

    JudgeDTO CreateJudge(JudgeDTO judgeDTO);

    JudgeDTO GetJudgeById(Long id);

    List<JudgeDTO> GetAllJudges();

    JudgeDTO UpdateJudge(Long id, JudgeDTO judgeDTO);

    void DeleteJudge(Long id);
}