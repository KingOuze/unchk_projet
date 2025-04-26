package com.example.tuto.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tuto.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(String email);

    List<User> findByArchived(boolean b);

    List<User> findByRole(String role);

    List<User> findByRoleAndArchived(String role, boolean archived);

     @Query("SELECT u.role, COUNT(u) FROM User u GROUP BY u.role")
    List<Object[]> countUsersByRole();

    @Query("SELECT u FROM User u WHERE " +
    "(LOWER(u.nom) LIKE LOWER(CONCAT('%', :searchTerm,'%')) OR " +
    "LOWER(u.prenom) LIKE LOWER(CONCAT('%', :searchTerm,'%')) OR " +
    "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm,'%'))) " +
    "AND (:role IS NULL OR :role = 'all' OR u.role = :role)")
    Page<User> findFilteredUsers(
    @Param("searchTerm") String searchTerm,
    @Param("role") String role,
    Pageable pageable
    );

    
}
