package com.example.tuto.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tuto.config.TokenBlacklist;
import com.example.tuto.exception.dto.AuthRequest;
import com.example.tuto.exception.dto.AuthResponse;
import com.example.tuto.security.JwtUtils;
import com.example.tuto.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final TokenBlacklist tokenBlacklist;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserService userService, TokenBlacklist tokenBlacklist) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.tokenBlacklist = tokenBlacklist;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(), 
                    request.getPassword()));
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            //recuperer l'id de l'utilisateur
            Long userId = userService.getUserByEmail(userDetails.getUsername());
            LoggerFactory.getLogger(AuthController.class).info("User ID: " + userId);
            String token = jwtUtils.generateToken(userDetails);
            
            return ResponseEntity.ok(new AuthResponse(
                token, 
                userId, 
                userDetails.getAuthorities()));
            
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = jwtUtils.parseJwt(request);
        if (token != null) {
            tokenBlacklist.addToBlacklist(token);
        }
        return ResponseEntity.ok("Logged out successfully");
    }
}