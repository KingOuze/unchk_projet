package com.example.tuto.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import jakarta.persistence.Id;


@Entity
@Data
public class Etudiant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ine;
    private Date dateNaiss;

    @ManyToOne
    private Formation formation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIne() {
        return ine;
    }
    public void setIne(String ine) {
        this.ine = ine;
    }
    public Date getDateNaiss() {
        return dateNaiss;
    }
    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }
    public Formation getFormation() {
        return formation;
    }
    public void setFormation(Formation formation) {
        this.formation = formation;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

