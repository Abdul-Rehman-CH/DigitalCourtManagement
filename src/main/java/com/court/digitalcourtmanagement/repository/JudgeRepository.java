package com.court.digitalcourtmanagement.repository;

import com.court.digitalcourtmanagement.entity.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JudgeRepository extends JpaRepository<Judge, Long> {
    Optional<Judge> findByEmail(String email);


    @Query("""
        SELECT j FROM Judge j
        WHERE j.status = com.court.digitalcourtmanagement.entity.Status.ACTIVE
        ORDER BY (
            SELECT COUNT(c) FROM CourtCase c
            WHERE c.judgeAssigned = j
              AND c.status NOT IN ('CLOSED', 'DISMISSED')
        ) ASC
    """)
    List<Judge> findActiveJudgesOrderedByActiveCaseload();
}
