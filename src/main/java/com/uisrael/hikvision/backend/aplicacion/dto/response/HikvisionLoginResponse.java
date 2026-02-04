package com.uisrael.hikvision.backend.aplicacion.dto.response;

public record HikvisionLoginResponse(
        boolean authenticated,
        String deviceName,
        String model,
        String serialNumber,
        String macAddress,
        String firmwareVersion
) {}
