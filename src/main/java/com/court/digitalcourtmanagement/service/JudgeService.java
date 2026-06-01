package com.court.digitalcourtmanagement.service;
import java.util.List;

import com.court.digitalcourtmanagement.entity.Judge;
public interface JudgeService {
    Judge CreateJudge(Judge j);
    Judge GetJudgeById(long jid);
    List<Judge> GetAllJudges();

    Judge UpdateJudge(long jid, Judge judge);
    void DeleteJudge(long jid);

    List<?> GetAssignedCases(Long judgeId);
}