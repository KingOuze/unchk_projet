package com.example.tuto.exception.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class EtudiantDTO extends UserDTO {
    private String ine;
    private Date dateNaiss;
    private Long formation_id;
}   