package com.jules.financeapp.dto;

public record LoginRequest(
        String username,
        String password
) {}