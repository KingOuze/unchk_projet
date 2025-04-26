package com.example.tuto.exception.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FormateurDTO extends UserDTO {
    private String specialite;
}
