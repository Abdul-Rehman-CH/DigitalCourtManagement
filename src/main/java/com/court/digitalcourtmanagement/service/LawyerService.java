package com.court.digitalcourtmanagement.service;

import java.util.List;

import com.court.digitalcourtmanagement.entity.Lawyer;

public interface LawyerService {

    Lawyer CreateLawyer(Lawyer l);

    Lawyer GetLawyerById(Long lid);

    List<Lawyer> GetAllLawyers();

    Lawyer UpdateLawyer(Long lid, Lawyer lawyer);

    void DeleteLawyer(Long ld);

    List<?> GetAssignedCases(Long lid);
}