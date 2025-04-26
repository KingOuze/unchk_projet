package com.example.tuto.exception.dto;


import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class AuthResponse {
    private String token;
    private Long userId;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor, Getters and Setters
    public AuthResponse(String token, Long userId, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.userId = userId;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}