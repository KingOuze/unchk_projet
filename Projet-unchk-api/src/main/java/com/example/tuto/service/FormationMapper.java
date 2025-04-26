package com.example.tuto.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.example.tuto.entities.Formation;
import com.example.tuto.exception.dto.FormationDTO;

@Component
public class FormationMapper {

    private final ModelMapper modelMapper;

    public FormationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Convert a Formation entity to a FormationDTO.
     *
     * @param formation the Formation entity
     * @return the FormationDTO
     */
    public FormationDTO toDto(Formation formation) {
        return modelMapper.map(formation, FormationDTO.class);
    }

    /**
     * Convert a FormationDTO to a Formation entity.
     *
     * @param formationDTO the FormationDTO
     * @return the Formation entity
     */
    public Formation toEntity(FormationDTO formationDTO) {
        return modelMapper.map(formationDTO, Formation.class);
    }

    /**
     * Update an existing Formation entity with data from a FormationDTO.
     *
     * @param formationDTO the FormationDTO with updated data
     * @param formation the Formation entity to update
     */
    public void updateEntity(FormationDTO formationDTO, Formation formation) {
        modelMapper.map(formationDTO, formation);
    }
}
