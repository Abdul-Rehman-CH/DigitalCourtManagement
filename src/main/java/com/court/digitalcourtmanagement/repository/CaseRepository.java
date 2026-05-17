package com.court.digitalcourtmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.court.digitalcourtmanagement.entity.CourtCase;

public interface CaseRepository extends JpaRepository<CourtCase, Long> {
}