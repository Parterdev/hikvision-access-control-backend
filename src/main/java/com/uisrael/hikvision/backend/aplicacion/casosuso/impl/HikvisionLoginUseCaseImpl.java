package com.uisrael.hikvision.backend.aplicacion.casosuso.impl;

import com.uisrael.hikvision.backend.aplicacion.casosuso.entradas.HikvisionLoginUseCase;
import com.uisrael.hikvision.backend.aplicacion.dto.request.HikvisionLoginRequest;
import com.uisrael.hikvision.backend.aplicacion.dto.response.HikvisionLoginResponse;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.dto.DeviceInfoXml;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.servicio.HikvisionDeviceInfoService;
import org.springframework.stereotype.Service;

@Service
public class HikvisionLoginUseCaseImpl implements HikvisionLoginUseCase {

    private final HikvisionDeviceInfoService deviceInfoService;

    public HikvisionLoginUseCaseImpl(HikvisionDeviceInfoService deviceInfoService) {
        this.deviceInfoService = deviceInfoService;
    }

    @Override
    public HikvisionLoginResponse login(HikvisionLoginRequest request) {
        try {
            DeviceInfoXml info = deviceInfoService.obtenerDeviceInfo(
                    request.baseUrl(),
                    request.username(),
                    request.password()
            );

            return new HikvisionLoginResponse(
                    true,
                    info.getDeviceName(),
                    info.getModel(),
                    info.getSerialNumber(),
                    info.getMacAddress(),
                    info.getFirmwareVersion()
            );

        } catch (Exception e) {
            e.printStackTrace(); // o logger
            return new HikvisionLoginResponse(false, null, null, null, null, null);
        }
    }
}
