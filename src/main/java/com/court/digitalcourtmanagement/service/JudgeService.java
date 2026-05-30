package com.court.digitalcourtmanagement.service;
import java.util.List;

import com.court.digitalcourtmanagement.entity.CourtCase;
import com.court.digitalcourtmanagement.entity.Judge;

public interface JudgeService {
    Judge CreateJudge(Judge j);

    Judge GetJudgetByID(long jid);

    Judge GetAllJudges();

    void UpdateJudge(long jid,Judge cl);

    void DeleteJudge(long jid);

    List<CourtCase> getAssignedCases(Long judgeId);
}
