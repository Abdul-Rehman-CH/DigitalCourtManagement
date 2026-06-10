package com.court.digitalcourtmanagement.service;

import com.court.digitalcourtmanagement.dto.LawyerDTO;
import com.court.digitalcourtmanagement.entity.Lawyer;
import com.court.digitalcourtmanagement.entity.Status;
import com.court.digitalcourtmanagement.Mapper.mappers;
import com.court.digitalcourtmanagement.repository.CaseRepository;
import com.court.digitalcourtmanagement.repository.LawyerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LawyerServiceImpl implements LawyerService {

    private final LawyerRepository lawyerRepository;
    private final CaseRepository caseRepository;

    public LawyerServiceImpl(LawyerRepository lawyerRepository, CaseRepository caseRepository) {
        this.lawyerRepository = lawyerRepository;
        this.caseRepository = caseRepository;
    }

    @Override
    public LawyerDTO createLawyer(LawyerDTO dto) {
        Lawyer l = new Lawyer();
        l.setName(dto.getName());
        l.setEmail(dto.getEmail());
        l.setContactNo(dto.getContactNo());
        l.setBarNumber(dto.getBarNumber());
        l.setSpecialization(dto.getSpecialization());
        if (dto.getStatus() != null) l.setStatus(Status.valueOf(dto.getStatus()));
        else l.setStatus(Status.ACTIVE);
        return mappers.toDTO(lawyerRepository.save(l));
    }

    @Override
    @Transactional(readOnly = true)
    public LawyerDTO getLawyerById(Long id) {
        return mappers.toDTO(lawyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lawyer not found with id: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LawyerDTO> getAllLawyers() {
        return lawyerRepository.findAll().stream().map(mappers::toDTO).collect(Collectors.toList());
    }

    @Override
    public LawyerDTO updateLawyer(Long id, LawyerDTO dto) {
        Lawyer l = lawyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lawyer not found with id: " + id));
        l.setName(dto.getName());
        l.setEmail(dto.getEmail());
        l.setContactNo(dto.getContactNo());
        l.setBarNumber(dto.getBarNumber());
        l.setSpecialization(dto.getSpecialization());
        if (dto.getStatus() != null) l.setStatus(Status.valueOf(dto.getStatus()));
        return mappers.toDTO(lawyerRepository.save(l));
    }

    @Override
    public void deleteLawyer(Long id) {
        lawyerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<?> getAssignedCases(Long id) {
       
        return caseRepository.findByLawyerAssigned_Id(id).stream()
                .map(mappers::toDTO).collect(Collectors.toList());
    }
}