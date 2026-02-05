package com.uisrael.hikvision.backend.dominio.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
@Entity
@Table(name = "device_connection", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"base_url", "username"})
})
public class DeviceConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="base_url", nullable=false)
    private String baseUrl;

    @Column(nullable=false)
    private String username;

    // password cifrada
    @Column(name="password_cipher", nullable=false, length=2048)
    private String passwordCipher;

    @Column(name="password_iv", nullable=false, length=256)
    private String passwordIv;

    // info del dispositivo
    private String deviceName;
    private String model;
    private String serialNumber;
    private String macAddress;
    private String firmwareVersion;

    @Column(name="updated_at", nullable=false)
    private Instant updatedAt = Instant.now();
}
