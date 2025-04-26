package com.example.tuto.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.tuto.entities.User;
import com.example.tuto.exception.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDTO convertToDto(User createdUser) {
        UserDTO dto = modelMapper.map(createdUser, UserDTO.class);
        
       /*if(createdUser.getEtudiant() != null) {
            EtudiantDTO etudiantDTO = modelMapper.map(createdUser, EtudiantDTO.class);
            etudiantDTO.setIne(createdUser.getEtudiant().getIne());
            etudiantDTO.setDateNaiss(createdUser.getEtudiant().getDateNaiss());
            etudiantDTO.setFormation_id(createdUser.getEtudiant().getFormation().getId());
            return etudiantDTO;
        }
        
        if(createdUser.getFormateur() != null) {
            FormateurDTO formateurDTO = modelMapper.map(createdUser, FormateurDTO.class);
            formateurDTO.setSpecialite(createdUser.getFormateur().getSpecialite());
            return formateurDTO;
        }*/
        
        return dto;
    }

    public User convertToEntity(UserDTO dto) {
        User user = modelMapper.map(dto, User.class);
        
        /*if(dto instanceof EtudiantDTO) {
            Etudiant etudiant = new Etudiant();
            etudiant.setIne(((EtudiantDTO) dto).getIne());
            etudiant.setDateNaiss(((EtudiantDTO) dto).getDateNaiss());
            user.setEtudiant(etudiant);
        }
        
        if(dto instanceof FormateurDTO) {
            Formateur formateur = new Formateur();
            formateur.setSpecialite(((FormateurDTO) dto).getSpecialite());
            user.setFormateur(formateur);
        }*/
        
        return user;
    }

    public void updateEntity(UserDTO dto, User user) {
        modelMapper.map(dto, user);
        
        /*if(dto instanceof EtudiantDTO) {
            Etudiant etudiant = user.getEtudiant();
            etudiant.setIne(((EtudiantDTO) dto).getIne());
            etudiant.setDateNaiss(((EtudiantDTO) dto).getDateNaiss());
            user.setEtudiant(etudiant);
        }
        
        if(dto instanceof FormateurDTO) {
            Formateur formateur = user.getFormateur();
            formateur.setSpecialite(((FormateurDTO) dto).getSpecialite());
            user.setFormateur(formateur);
        }*/
    }
}