package com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios;

import com.uisrael.hikvision.backend.dominio.entidades.DeviceConnection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceConnectionRepository extends JpaRepository<DeviceConnection, Long> {
    Optional<DeviceConnection> findByBaseUrlAndUsername(String baseUrl, String username);
}
