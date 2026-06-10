package com.court.digitalcourtmanagement.repository;

import com.court.digitalcourtmanagement.entity.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JudgeRepository extends JpaRepository<Judge, Long> {
    Optional<Judge> findByEmail(String email);
}