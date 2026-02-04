package com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikvisionXmlConfig {

    @Bean
    public XmlMapper hikvisionXmlMapper() {
        return new XmlMapper();
    }
}
