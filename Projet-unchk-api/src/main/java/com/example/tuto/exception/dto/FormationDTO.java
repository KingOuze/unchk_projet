package com.example.tuto.exception.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import com.example.tuto.entities.Formation.StatusFormation;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class FormationDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String nom;
    
    @NotBlank
    private String niveau;
    
    @NotBlank
    private String type;
    
    private Date dateDebut;
    
    private Date dateFin;
    
    @Null
    private Long userId;
    
    private Boolean archived = false; // Nouveau champ pour l'archivage
        
    private StatusFormation status;

    //getter and setter methods
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
    
    public String getNiveau() {
        return niveau;
    }
    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Boolean getArchived() {
        return archived;
    }
    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public StatusFormation getStatus() {
        return status;
    }
    public void setStatus(StatusFormation status) {
        this.status = status;
    }

    
}

