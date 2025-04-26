package com.example.tuto.controller;



import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tuto.entities.User;
import com.example.tuto.enums.ErrorCode;
import com.example.tuto.exception.custom.BusinessException;
import com.example.tuto.exception.dto.UserDTO;
import com.example.tuto.service.UserMapper;
import com.example.tuto.service.UserService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = (List<User>) userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
                    /*.orElseThrow(() -> new ResourceNotFoundException("User", "id", id)); */
        return ResponseEntity.ok(userMapper.convertToDto(user));
    }

   /* @GetMapping("email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        Long user = userService.getUserByEmail(email);
                    /*.orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return ResponseEntity.ok(userMapper.convertToDto(user));
    }*/

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        
        if (userService.existsByEmail(userDTO.getEmail())) {
            throw new BusinessException("Email déjà utilisé", ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        
        User user = userMapper.convertToEntity(userDTO);
        logger.info("User entity before save: {}", userDTO.getRole());
        User createdUser = userService.createUser(user);

        // Handle the case where the user is not created successfully   
        if (createdUser == null) {
            throw new BusinessException("Erreur lors de la création de l'utilisateur", ErrorCode.USER_CREATION_FAILED);
        }
        return new ResponseEntity<>(userMapper.convertToDto(createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User existingUser = userService.getUserById(id);
            /* .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));*/

        // Vérifiez que l'identifiant n'est pas modifié
        /*if (userDTO.getId() != null && !userDTO.getId().equals(id)) {
            throw new IllegalArgumentException("L'identifiant de l'utilisateur ne peut pas être modifié.");
        }*/
        
        // Mettez à jour l'utilisateur existant avec les nouvelles données
        userMapper.updateEntity(userDTO, existingUser);
        User updatedUser = userService.updateUser(id, existingUser);
        
        return ResponseEntity.ok(userMapper.convertToDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
            /*.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));*/
        
        userService.deleteUser(user.getId());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<Void> archiveUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
            /*.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));*/
        
        userService.archiveUser(user.getId());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/unarchive")
    public ResponseEntity<Void> unarchiveUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
            /*.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));*/
        
        userService.unarchiveUser(user.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users-counts")
    public ResponseEntity<Map<String, Long>> getUsersCountByRole() {
        return ResponseEntity.ok(userService.getUsersCountByRole());
    }

}