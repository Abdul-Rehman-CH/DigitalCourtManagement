package com.court.digitalcourtmanagement.repository;

import com.court.digitalcourtmanagement.entity.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    Optional<Lawyer> findByEmail(String email);
}