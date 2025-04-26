package com.example.tuto.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.tuto.service.FormationService;
import com.example.tuto.entities.Formation;
import com.example.tuto.entities.Formation.StatusFormation;
import com.example.tuto.exception.dto.FormationDTO;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
public class FormationController {

    private final FormationService formationService;

    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }

    @GetMapping
    public List<FormationDTO> getAllFormations() {
        return formationService.getAllFormations();
    }

    @GetMapping("/{id}")
    public FormationDTO getFormationById(@PathVariable Long id) {
        return formationService.getFormationById(id);
    }

    @PostMapping
    public FormationDTO createFormation(@RequestBody FormationDTO formationDTO) {
        return formationService.createFormation(formationDTO);
    }

    @PutMapping("/{id}")
    public FormationDTO updateFormation(@PathVariable Long id, @RequestBody FormationDTO formationDTO) {
        return formationService.updateFormation(id, formationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
    }

   @GetMapping("/user/{userId}")
    public ResponseEntity<List<Formation>> getFormationsByUserId(@PathVariable Long userId) {
        try {
            List<Formation> formations = formationService.getFormationsByUserId(userId);
            return ResponseEntity.ok(formations);
        } catch (RuntimeException e) {
            Logger logger = LoggerFactory.getLogger(FormationController.class);
            logger.error("Error retrieving formations for user ID {}: {}", userId, e.getMessage());
            return ResponseEntity.status(201).body(null); // Retourne une réponse 404 si aucune formation n'est trouvée
        }
    }
   
    @PatchMapping("/{id}/archive")
    public void archiveFormation(@PathVariable Long id) {

        try {
            formationService.archiveFormation(id);
        } catch (RuntimeException e) {
            Logger logger = LoggerFactory.getLogger(FormationController.class);
            logger.error("Error archiving formation with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error archiving formation with ID " + id);
        }
    }
    @PatchMapping("/{id}/unarchive")
    public void unarchiveFormation(@PathVariable Long id) {
        try {
            formationService.unarchiveFormation(id);
        } catch (RuntimeException e) {
            Logger logger = LoggerFactory.getLogger(FormationController.class);
            logger.error("Error unarchiving formation with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error unarchiving formation with ID " + id);
        }
    }

    @PatchMapping("/{id}/status")
    public void updateFormationStatus(@PathVariable Long id, @RequestParam StatusFormation status) {
        formationService.changeFormationStatus(id, status);
    }
}

