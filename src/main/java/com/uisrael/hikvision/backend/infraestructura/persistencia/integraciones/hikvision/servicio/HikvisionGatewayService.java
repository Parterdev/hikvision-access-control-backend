package com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.servicio;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.uisrael.hikvision.backend.dominio.entidades.DeviceConnection;
import com.uisrael.hikvision.backend.infraestructura.configuracion.seguridad.CryptoService;
import com.uisrael.hikvision.backend.infraestructura.configuracion.seguridad.SessionResolverService;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.cliente.HikvisionDigestClient;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.dto.DeviceInfoXml;
import org.springframework.stereotype.Service;

@Service
public class HikvisionGatewayService {

    private final SessionResolverService sessionResolver;
    private final CryptoService crypto;
    private final HikvisionDigestClient client;
    private final XmlMapper xmlMapper;

    public HikvisionGatewayService(SessionResolverService sessionResolver,
                                   CryptoService crypto,
                                   HikvisionDigestClient client,
                                   XmlMapper xmlMapper) {
        this.sessionResolver = sessionResolver;
        this.crypto = crypto;
        this.client = client;
        this.xmlMapper = xmlMapper;
    }

    public String getRaw(String path) {
        DeviceConnection conn = sessionResolver.currentDeviceConnection();
        String password = crypto.decrypt(conn.getPasswordCipher(), conn.getPasswordIv());

        return client.get(
                conn.getBaseUrl(),
                path,
                conn.getUsername(),
                password
        );
    }
    public DeviceInfoXml getDeviceInfo() {
        try {
            String xml = getRaw("/ISAPI/System/deviceInfo");
            return xmlMapper.readValue(xml, DeviceInfoXml.class);
        } catch (Exception e) {
            throw new RuntimeException("Error parseando DeviceInfo desde Hikvision", e);
        }
    }
}


