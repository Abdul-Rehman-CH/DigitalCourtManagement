package com.court.digitalcourtmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.court.digitalcourtmanagement.entity.CourtCase;
import java.util.List;

public interface CaseRepository extends JpaRepository<CourtCase, Long> {
        List<CourtCase> findByClientId(Long id);
}