package com.example.tuto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.tuto.entities.History;


public interface HistoryRepository extends JpaRepository<History, Long> {

    // Method to find the most recent login history for a user that has not been logged out
    @Query("SELECT h FROM History h WHERE h.userId = :userId AND h.logoutTime IS NULL ORDER BY h.loginTime DESC")
    Optional<History> findTopByUserIdAndLogoutTimeIsNullOrderByLoginTimeDesc(Long userId);
}
