package com.uisrael.hikvision.backend.dominio.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter @Setter
@Entity
@Table(name="auth_session")
public class AuthSession {

    @Id
    private UUID id;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="device_connection_id")
    private DeviceConnection deviceConnection;

    @Column(name="issued_at", nullable=false)
    private Instant issuedAt;

    @Column(name="expires_at", nullable=false)
    private Instant expiresAt;

    @Column(nullable=false)
    private boolean active = true;
}
