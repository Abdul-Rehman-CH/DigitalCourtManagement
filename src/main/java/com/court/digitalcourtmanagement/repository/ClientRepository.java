package com.court.digitalcourtmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.court.digitalcourtmanagement.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}