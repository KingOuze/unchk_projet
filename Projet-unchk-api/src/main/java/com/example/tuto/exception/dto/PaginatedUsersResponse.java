package com.example.tuto.exception.dto;

import java.util.List;

import com.example.tuto.entities.User;

// PaginatedUsersResponse.java (DTO)
public class PaginatedUsersResponse {
    private List<User> data;
    private long total;
    private int page;
    private int limit;
    private int totalPages;

    public PaginatedUsersResponse(List<User> data, long total, int page, int limit, int totalPages) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
        this.totalPages = totalPages;
    }

    // Getters
}
