package com.jules.financeapp.dto;

public record RegisterRequest(
        String username,
        String email,
        String password
) {}
