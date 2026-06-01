package com.court.digitalcourtmanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.court.digitalcourtmanagement.entity.CourtCase;

public interface CaseRepository extends JpaRepository<CourtCase, Long> {

    List<CourtCase> findByClient_Id(Long id);

    List<CourtCase> findByLawyerAssigned_Id(Long id);

    List<CourtCase> findByJudgeAssigned_Id(Long id);
}