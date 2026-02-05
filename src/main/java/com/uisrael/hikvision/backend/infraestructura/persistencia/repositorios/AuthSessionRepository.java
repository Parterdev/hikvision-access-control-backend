package com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios;

import com.uisrael.hikvision.backend.dominio.entidades.AuthSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthSessionRepository extends JpaRepository<AuthSession, UUID> {
    Optional<AuthSession> findByIdAndActiveTrue(UUID id);
}

