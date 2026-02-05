package com.uisrael.hikvision.backend.aplicacion.casosuso.impl;

import com.uisrael.hikvision.backend.aplicacion.casosuso.entradas.HikvisionLoginUseCase;
import com.uisrael.hikvision.backend.aplicacion.dto.request.HikvisionLoginRequest;
import com.uisrael.hikvision.backend.aplicacion.dto.response.HikvisionLoginResponse;
import com.uisrael.hikvision.backend.dominio.entidades.AuthSession;
import com.uisrael.hikvision.backend.dominio.entidades.DeviceConnection;
import com.uisrael.hikvision.backend.infraestructura.configuracion.seguridad.CryptoService;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.dto.DeviceInfoXml;
import com.uisrael.hikvision.backend.infraestructura.persistencia.integraciones.hikvision.servicio.HikvisionDeviceInfoService;
import com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios.AuthSessionRepository;
import com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios.DeviceConnectionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class HikvisionLoginUseCaseImpl implements HikvisionLoginUseCase {

    private final HikvisionDeviceInfoService deviceInfoService;
    private final JwtEncoder jwtEncoder;
    private final long expirationSeconds;

    private final DeviceConnectionRepository deviceRepo;
    private final AuthSessionRepository sessionRepo;
    private final CryptoService crypto;

    public HikvisionLoginUseCaseImpl(
            HikvisionDeviceInfoService deviceInfoService,
            JwtEncoder jwtEncoder,
            @Value("${security.jwt.expiration-seconds:3600}") long expirationSeconds,
            DeviceConnectionRepository deviceRepo,
            AuthSessionRepository sessionRepo,
            CryptoService crypto
    ) {
        this.deviceInfoService = deviceInfoService;
        this.jwtEncoder = jwtEncoder;
        this.expirationSeconds = expirationSeconds;
        this.deviceRepo = deviceRepo;
        this.sessionRepo = sessionRepo;
        this.crypto = crypto;
    }

    @Override
    public HikvisionLoginResponse login(HikvisionLoginRequest request) {
        try {
            // 1) Validar credenciales contra Hikvision
            DeviceInfoXml info = deviceInfoService.obtenerDeviceInfo(
                    request.baseUrl(),
                    request.username(),
                    request.password()
            );

            // 2) Upsert DeviceConnection (guardamos password cifrada)
            var enc = crypto.encrypt(request.password());

            DeviceConnection conn = deviceRepo
                    .findByBaseUrlAndUsername(request.baseUrl(), request.username())
                    .orElseGet(DeviceConnection::new);

            conn.setBaseUrl(request.baseUrl());
            conn.setUsername(request.username());
            conn.setPasswordCipher(enc.cipherText());
            conn.setPasswordIv(enc.iv());
            conn.setDeviceName(info.getDeviceName());
            conn.setModel(info.getModel());
            conn.setSerialNumber(info.getSerialNumber());
            conn.setMacAddress(info.getMacAddress());
            conn.setFirmwareVersion(info.getFirmwareVersion());
            conn.setUpdatedAt(Instant.now());

            conn = deviceRepo.save(conn);

            // 3) Crear sesión (sid)
            Instant now = Instant.now();
            Instant exp = now.plusSeconds(expirationSeconds);
            UUID sid = UUID.randomUUID();

            AuthSession session = new AuthSession();
            session.setId(sid);
            session.setDeviceConnection(conn);
            session.setIssuedAt(now);
            session.setExpiresAt(exp);
            session.setActive(true);

            sessionRepo.save(session);

            // 4) Token con claim sid
            String token = generarToken(request.username(), request.baseUrl(), sid, now, exp);

            // 5) Respuesta
            return new HikvisionLoginResponse(
                    true,
                    token,
                    expirationSeconds,
                    info.getDeviceName(),
                    info.getModel(),
                    info.getSerialNumber(),
                    info.getMacAddress(),
                    info.getFirmwareVersion()
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new HikvisionLoginResponse(false, null, 0, null, null, null, null, null);
        }
    }

    private String generarToken(String username, String baseUrl, UUID sid, Instant now, Instant exp) {

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("hikvision.backend")
                .issuedAt(now)
                .expiresAt(exp)
                .subject(username)
                .claim("baseUrl", baseUrl)
                .claim("sid", sid.toString())
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256)
                .type("JWT")
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}

