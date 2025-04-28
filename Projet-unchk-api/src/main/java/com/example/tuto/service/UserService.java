package com.example.tuto.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.example.tuto.entities.User;
import com.example.tuto.enums.userRole;
import com.example.tuto.exception.dto.PaginatedUsersResponse;
import com.example.tuto.repository.EtudiantRepository;
import com.example.tuto.repository.FormateurRepository;
import com.example.tuto.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository ;
    private final EtudiantRepository etudiantRepository;
    private final FormateurRepository formateurRepository;

    private final PasswordEncoder passwordEncoder;


    // Create
    public User createUser(User user) {
        // Hashing the password
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User savedUser = userRepository.save(user);
        
       /*  if ("ETUD".equals(user.getRole())) {
            Etudiant etudiant = new Etudiant();
            etudiant.setUser(savedUser);
            logger.info("User entity before save: {}", etudiant);
            etudiantRepository.save(etudiant);
        } else if ("FORMA".equals(user.getRole())) {
            Formateur formateur = new Formateur();
            formateur.setUser(savedUser);
            formateur.setSpecialite(user.getFormateur().getSpecialite());
            //Assuming the specialite is set in the Formateur entity
            //You might want to set other properties as well
            formateur.setSpecialite(user.getFormateur().getSpecialite());
            
            formateurRepository.save(formateur);
        }*/
        
        return savedUser;
    }

    // Read
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public PaginatedUsersResponse getFilteredUsers(int page, int limit, String search, String role) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<User> userPage = userRepository.findFilteredUsers(
            search,
            role,
            pageable
        );

        return new PaginatedUsersResponse(
            userPage.getContent(),
            userPage.getTotalElements(),
            page,
            limit,
            userPage.getTotalPages()
        );
    }
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setNom(userDetails.getNom());
        user.setPrenom(userDetails.getPrenom());
        user.setEmail(userDetails.getEmail());
        user.setRole(userDetails.getRole());
        user.setIne(userDetails.getIne());
        user.setDateNais(userDetails.getDateNais());
        user.setFormation_id(userDetails.getFormation_id());
        user.setSpecialite(userDetails.getSpecialite());

        return userRepository.save(user);
    }

    // Delete
    
    public void deleteUser(Long id) {
        User user = getUserById(id);
        
        // Suppression des entités liées
        /*if(user.getEtudiant() != null) {
            etudiantRepository.delete(user.getEtudiant());
        }
        if(user.getFormateur() != null) {
            formateurRepository.delete(user.getFormateur());
        }*/
        
        userRepository.delete(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    public Long getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

    //archive
    public void archiveUser(Long id) {
        User user = getUserById(id);
        user.setArchived(true);
        userRepository.save(user);
    }
    // Unarchive
    
    public void unarchiveUser(Long id) {
        User user = getUserById(id);
        user.setArchived(false);
        userRepository.save(user);
    }

    // Get archived users
    public List<User> getArchivedUsers() {
        return userRepository.findByArchived(true);
    }
    // Get non-archived users
    public List<User> getNonArchivedUsers() {
        return userRepository.findByArchived(false);
    }
    
    // Get users by role
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
    // Get users by role and archived status
    public List<User> getUsersByRoleAndArchived(String role, boolean archived) {
        return userRepository.findByRoleAndArchived(role, archived);
    }

    // Get user count by role
    public Map<String, Long> getUsersCountByRole() {
        Map<String, Long> counts = Arrays.stream(userRole.values())
            .collect(Collectors.toMap(
                Enum::name,
                role -> 0L
            ));

        userRepository.countUsersByRole().forEach(arr -> {
            String role = arr[0].toString();
            counts.put(role, (Long) arr[1]);
        });

        return counts;
    }
   
}
