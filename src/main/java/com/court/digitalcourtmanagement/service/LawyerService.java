package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.LawyerDTO;
import java.util.List;

public interface LawyerService {
    LawyerDTO createLawyer(LawyerDTO dto);
    LawyerDTO getLawyerById(Long id);
    List<LawyerDTO> getAllLawyers();
    LawyerDTO updateLawyer(Long id, LawyerDTO dto);
    void deleteLawyer(Long id);
    List<?> getAssignedCases(Long id);
}