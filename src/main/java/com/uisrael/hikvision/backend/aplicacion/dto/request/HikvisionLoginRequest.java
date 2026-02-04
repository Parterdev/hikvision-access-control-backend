package com.uisrael.hikvision.backend.aplicacion.dto.request;

import jakarta.validation.constraints.NotBlank;

public record HikvisionLoginRequest(
        @NotBlank String baseUrl,
        @NotBlank String username,
        @NotBlank String password
) {}
