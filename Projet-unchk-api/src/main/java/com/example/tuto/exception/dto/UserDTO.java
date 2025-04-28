package com.example.tuto.exception.dto;


import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.example.tuto.enums.userRole;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public userRole getRole() {
        return role;
    }
    public void setRole(userRole role) {
        this.role = role;
    }
    public Date getDate_naiss() {
        return date_naiss;
    }
    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }
    public Long getFormation_id() {
        return formation_id;
    }
    public void setFormation_id(Long formation_id) {
        this.formation_id = formation_id;
    }
    public String getIne() {
        return ine;
    }
    public void setIne(String ine) {
        this.ine = ine;
    }
    public String getSpecialite() {
        return specialite;
    }
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    public Boolean getArchived() {
        return archived;
    }
    public void setArchived(Boolean archived) {
        this.archived = archived;
    }
   
}