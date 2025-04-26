package com.example.tuto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tuto.exception.dto.ComptRenduDTO;
import com.example.tuto.service.ComptRenduService;

@RestController
@RequestMapping("/api/comptRendu")
public class ComptRenduController {

    private final ComptRenduService comptRenduService;

    public ComptRenduController(ComptRenduService comptRenduService) {
        this.comptRenduService = comptRenduService;
    }

    @GetMapping
    public List<ComptRenduDTO> getAllComptRendus() {
        return comptRenduService.getAllComptRendus();
    }

    @GetMapping("/{id}")
    public ComptRenduDTO getComptRenduById(@PathVariable Long id) {
        return comptRenduService.getComptRenduById(id);
    }

    @PostMapping
    public ComptRenduDTO createComptRendu(@RequestBody ComptRenduDTO comptRenduDTO) {
        return comptRenduService.createComptRendu(comptRenduDTO);
    }

    @PutMapping("/{id}")
    public ComptRenduDTO updateComptRendu(@PathVariable Long id, @RequestBody ComptRenduDTO comptRenduDTO) {
        return comptRenduService.updateComptRendu(id, comptRenduDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComptRendu(@PathVariable Long id) {
        comptRenduService.deleteComptRendu(id);
    }
    
}
