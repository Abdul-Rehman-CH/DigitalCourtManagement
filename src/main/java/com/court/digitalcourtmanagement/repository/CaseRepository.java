package com.court.digitalcourtmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.court.digitalcourtmanagement.entity.Case;

public interface CaseRepository extends JpaRepository<Case, Long> {
}