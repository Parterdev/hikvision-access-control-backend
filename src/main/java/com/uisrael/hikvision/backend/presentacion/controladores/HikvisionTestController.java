package com.uisrael.hikvision.backend.presentacion.controladores;

import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.dto.DeviceInfoXml;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.servicio.HikvisionDeviceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hikvision")
@RequiredArgsConstructor
public class HikvisionTestController {

    private final HikvisionDeviceInfoService deviceInfoService;

    @GetMapping("/device-info")
    public DeviceInfoXml deviceInfo(
            @RequestParam String baseUrl,
            @RequestParam String username,
            @RequestParam String password
    ) {
        return deviceInfoService.obtenerDeviceInfo(baseUrl, username, password);
    }
}
