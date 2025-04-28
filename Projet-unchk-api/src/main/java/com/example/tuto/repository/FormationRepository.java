package com.example.tuto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.tuto.entities.Formation;

public interface FormationRepository extends JpaRepository<Formation, Long> {

    // Méthode pour récupérer les formations par userId
    @Query("SELECT f FROM Formation f WHERE f.userId = ?1")
    List<Formation> findByUserId(Long id);

    // Méthode pour récupérer les formations par status
    @Query("SELECT f FROM Formation f WHERE f.status = ?1")
    List<Formation> findByStatus(String status);
}
