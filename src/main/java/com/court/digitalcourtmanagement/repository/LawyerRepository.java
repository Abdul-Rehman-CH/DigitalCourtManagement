package com.court.digitalcourtmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.court.digitalcourtmanagement.entity.Lawyer;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
} 
