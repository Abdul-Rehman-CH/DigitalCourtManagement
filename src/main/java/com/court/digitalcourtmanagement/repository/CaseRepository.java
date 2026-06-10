package com.court.digitalcourtmanagement.repository;

import com.court.digitalcourtmanagement.entity.CourtCase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CaseRepository extends JpaRepository<CourtCase, Long> {


    List<CourtCase> findByClient_Id(Long clientId);
    List<CourtCase> findByJudgeAssigned_Id(Long judgeId);
    List<CourtCase> findByLawyerAssigned_Id(Long lawyerId);
}