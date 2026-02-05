package com.uisrael.hikvision.backend.infraestructura.configuracion.seguridad;

import com.uisrael.hikvision.backend.dominio.entidades.AuthSession;
import com.uisrael.hikvision.backend.dominio.entidades.DeviceConnection;
import com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios.AuthSessionRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class SessionResolverService {

    private final AuthSessionRepository sessionRepo;

    public SessionResolverService(AuthSessionRepository sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

    public DeviceConnection currentDeviceConnection() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof Jwt jwt)) {
            throw new RuntimeException("No hay JWT en el contexto de seguridad");
        }

        String sidStr = jwt.getClaimAsString("sid");
        if (sidStr == null || sidStr.isBlank()) {
            throw new RuntimeException("Token no contiene claim sid");
        }

        UUID sid = UUID.fromString(sidStr);
        AuthSession session = sessionRepo.findByIdAndActiveTrue(sid)
                .orElseThrow(() -> new RuntimeException("Sesión inválida o inactiva"));

        if (session.getExpiresAt().isBefore(Instant.now())) {
            session.setActive(false);
            sessionRepo.save(session);
            throw new RuntimeException("Sesión expirada");
        }

        return session.getDeviceConnection();
    }
}


