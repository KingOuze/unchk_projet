package com.example.tuto.exception.dto;


import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.example.tuto.enums.userRole;

import lombok.Data;

@Data
public class UserDTO {

    //@Null // L'ID est généré automatiquement
    //private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    @NotNull
    private userRole role;


    @Null
    private Date date_naiss;

    @Null
    private Long formation_id;

    @Null
    private String ine;

    @Null
    private String specialite;

    private Boolean archived = false; // Nouveau champ pour l'archivage
}