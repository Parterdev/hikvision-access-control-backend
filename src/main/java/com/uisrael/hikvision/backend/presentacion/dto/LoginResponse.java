package com.uisrael.hikvision.backend.presentacion.dto;

public record LoginResponse(
        boolean authenticated,
        String deviceName,
        String model,
        String serialNumber,
        String macAddress,
        String firmwareVersion
) {}
