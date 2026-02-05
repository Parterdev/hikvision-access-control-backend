package com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.servicio;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.cliente.HikvisionDigestClient;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.dto.DeviceInfoXml;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HikvisionDeviceInfoService {

    private final HikvisionDigestClient client;
    private final XmlMapper xmlMapper;

    public String obtenerDeviceInfoRaw(String baseUrl, String username, String password) {
        return client.get(baseUrl, "/ISAPI/System/deviceInfo", username, password);
    }

    public DeviceInfoXml obtenerDeviceInfo(String baseUrl, String username, String password) {
        try {
            String xml = obtenerDeviceInfoRaw(baseUrl, username, password);
            return xmlMapper.readValue(xml, DeviceInfoXml.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parseando DeviceInfo desde Hikvision", e);
        }
    }
}

