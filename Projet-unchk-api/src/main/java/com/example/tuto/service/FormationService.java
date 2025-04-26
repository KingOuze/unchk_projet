package com.example.tuto.service;

import org.springframework.stereotype.Service;
import com.example.tuto.entities.Formation;
import com.example.tuto.entities.Formation.StatusFormation;
import com.example.tuto.repository.FormationRepository;
import com.example.tuto.exception.dto.FormationDTO;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormationService {

    private final FormationRepository formationRepository;
    private final FormationMapper formationMapper;

    public FormationService(FormationRepository formationRepository, FormationMapper formationMapper) {
        this.formationRepository = formationRepository;
        this.formationMapper = formationMapper;
    }

    public List<FormationDTO> getAllFormations() {
        List<Formation> formations = formationRepository.findAll();
        return formations.stream()
                .map(formationMapper::toDto)
                .collect(Collectors.toList());
    }

    public FormationDTO getFormationById(Long id) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation not found"));
        return formationMapper.toDto(formation);
    }

    public FormationDTO createFormation(FormationDTO formationDTO) {
        Formation formation = formationMapper.toEntity(formationDTO);
        
        // show the formation
        System.out.println("Formation created: " + formation);
        Formation savedFormation = formationRepository.save(formation);
        return formationMapper.toDto(savedFormation);
    }

    public FormationDTO updateFormation(Long id, FormationDTO formationDTO) {
        Formation existingFormation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation not found"));
        formationMapper.toEntity(formationDTO);
        Formation updatedFormation = formationRepository.save(existingFormation);
        return formationMapper.toDto(updatedFormation);
    }

    public void deleteFormation(Long id) {
        if (!formationRepository.existsById(id)) {
            throw new RuntimeException("Formation not found");
        }
        formationRepository.deleteById(id);
    }

    //archiver une formation
    public void archiveFormation(Long id) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation not found"));
        formation.setArchived(true);
        formationRepository.save(formation);
    }

    //désarchiver une formation
    public void unarchiveFormation(Long id) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation not found"));
        formation.setArchived(false);
        formationRepository.save(formation);
    }

    //changer le statut d'une formation
    public void changeFormationStatus(Long id, StatusFormation status) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation not found"));
        formation.setStatus(status);
        formationRepository.save(formation);
    }

    //get formations by user_id
    public List<Formation> getFormationsByUserId(Long userId) {
        System.out.println("User ID: " + userId);  
        // Récupérer les formations par userId
        List<Formation> formations = formationRepository.findByUserId(userId);
        if (formations.isEmpty()) {
            throw new RuntimeException("Formation not found");
        }
        return formations;
    }
}
