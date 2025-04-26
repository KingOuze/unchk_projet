package com.example.tuto.exception.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import com.example.tuto.entities.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class ComptRenduDTO {

   @Null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String description;

    @NotBlank
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    
}
