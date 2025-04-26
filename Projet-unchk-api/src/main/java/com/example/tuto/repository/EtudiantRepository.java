package com.example.tuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tuto.entities.Etudiant;

@Repository
public interface EtudiantRepository  extends JpaRepository<Etudiant, Long>{

}
