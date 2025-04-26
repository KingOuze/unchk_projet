package com.example.tuto.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.tuto.entities.ComptRendu;
import com.example.tuto.exception.dto.ComptRenduDTO;

@Component
public class ComptRenduMapper {
     private final ModelMapper modelMapper;


    public ComptRenduMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ComptRenduDTO toDto(ComptRendu ComptRendu) {
        return modelMapper.map(ComptRendu, ComptRenduDTO.class);
    }

    public ComptRendu toEntity(ComptRenduDTO ComptRenduDTO) {
        return modelMapper.map(ComptRenduDTO, ComptRendu.class);
    }
}
