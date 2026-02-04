package com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JacksonXmlRootElement(localName = "DeviceInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInfoXml {

    @JacksonXmlProperty(localName = "deviceName")
    private String deviceName;

    @JacksonXmlProperty(localName = "model")
    private String model;

    @JacksonXmlProperty(localName = "serialNumber")
    private String serialNumber;

    @JacksonXmlProperty(localName = "macAddress")
    private String macAddress;

    @JacksonXmlProperty(localName = "firmwareVersion")
    private String firmwareVersion;
}
