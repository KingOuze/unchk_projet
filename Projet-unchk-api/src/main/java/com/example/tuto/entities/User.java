package com.example.tuto.entities;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.tuto.enums.userRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@SuppressWarnings("unused")
@Entity
@Data
    


public class User  implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Date date_naiss;
    private Long formation_id;
    private String ine;
    private String specialite;

   // @Enumerated(EnumType.STRING)
    private String role;

  /*  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Etudiant etudiant;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Formateur formateur ;
*/

    private Boolean archived = false; // Nouveau champ pour l'archivage


     // Getters and Setters

     public Long getId() {
        return id;
    }

    /*public void setId(Long id) {
        this.id = id;
    }*/

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

    public String getRole(String role) {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Boolean getArchived() {
        return archived;
    }
    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public void setIne(String ine){
        this.ine = ine;
    }

    public String getIne(){
        return ine;
    }

    public void setDateNais(Date date_naiss){
        this.date_naiss = date_naiss;
    }

    public Date getDateNais(){
        return date_naiss;
    }

    public void setSpecialite(String specialite){
        this.specialite = specialite;
    }

    public String get(){
        return specialite;
    }

    public void setFormation(Long formation_id){
        this.formation_id = formation_id;
    }

    public Long getFormation(){
        return formation_id;
    }


    // Implémentation des méthodes UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    
    /*public Formateur getFormateur() {
        return formateur;
    }
    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }
    public Etudiant getEtudiant() {
        return etudiant;
    }*/

}