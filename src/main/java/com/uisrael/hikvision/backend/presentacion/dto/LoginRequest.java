package com.uisrael.hikvision.backend.presentacion.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String baseUrl,     // ejemplo: http://192.168.1.3
        @NotBlank String username,    // admin
        @NotBlank String password     // Admin@1234root
) {}
