package com.court.digitalcourtmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.court.digitalcourtmanagement.entity.Judge;

public interface  JudgeRepository extends JpaRepository<Judge, Long> {
    
}
