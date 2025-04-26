package com.example.tuto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.tuto.entities.ComptRendu;
import com.example.tuto.exception.dto.ComptRenduDTO;
import com.example.tuto.repository.ComptRenduRepository;


@Service
public class ComptRenduService {

    private final ComptRenduRepository comptRenduRepository;
    private final ComptRenduMapper comptRenduMapper;

    public ComptRenduService(ComptRenduRepository comptRenduRepository, ComptRenduMapper comptRenduMapper) {
        this.comptRenduRepository = comptRenduRepository;
        this.comptRenduMapper = comptRenduMapper;
    }

    public List<ComptRenduDTO> getAllComptRendus() {
        List<ComptRendu> comptRendus = comptRenduRepository.findAll();
        return comptRendus.stream()
                .map(comptRenduMapper::toDto)
                .collect(Collectors.toList());
    }

    public ComptRenduDTO getComptRenduById(Long id) {
        ComptRendu comptRendu = comptRenduRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ComptRendu not found"));
        return comptRenduMapper.toDto(comptRendu);
    }

    public ComptRenduDTO createComptRendu(ComptRenduDTO comptRenduDTO) {
        ComptRendu comptRendu = comptRenduMapper.toEntity(comptRenduDTO);
        ComptRendu savedComptRendu = comptRenduRepository.save(comptRendu);
        return comptRenduMapper.toDto(savedComptRendu);
    }

    public ComptRenduDTO updateComptRendu(Long id, ComptRenduDTO comptRenduDTO) {
        ComptRendu existingComptRendu = comptRenduRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ComptRendu not found"));
        comptRenduMapper.toEntity(comptRenduDTO);
        ComptRendu updatedComptRendu = comptRenduRepository.save(existingComptRendu);
        return comptRenduMapper.toDto(updatedComptRendu);
    }

    public void deleteComptRendu(Long id) {
        if (!comptRenduRepository.existsById(id)) {
            throw new RuntimeException("ComptRendu not found");
        }
        comptRenduRepository.deleteById(id);
    }
}
