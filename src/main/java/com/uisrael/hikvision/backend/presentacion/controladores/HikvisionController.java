package com.uisrael.hikvision.backend.presentacion.controladores;

import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.dto.DeviceInfoXml;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.servicio.HikvisionGatewayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hikvision")
public class HikvisionController {

    private final HikvisionGatewayService gateway;

    public HikvisionController(HikvisionGatewayService gateway) {
        this.gateway = gateway;
    }

    @GetMapping(value = "/device-info/raw", produces = "application/xml")
    public String deviceInfoRaw() {
        return gateway.getRaw("/ISAPI/System/deviceInfo");
    }
    
    @GetMapping(value = "/device-info", produces = "application/json")
    public DeviceInfoXml deviceInfoJson() {
        return gateway.getDeviceInfo();
    }
}





