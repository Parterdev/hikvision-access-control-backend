package com.uisrael.hikvision.backend.infraestructura.configuracion.seguridad;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.*;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
public class JwtConfig {

    private byte[] secretBytes(String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("security.jwt.secret no está definido o está vacío");
        }

        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);

        // HS256 recomendado: >= 32 bytes
        if (bytes.length < 32) {
            throw new IllegalArgumentException("security.jwt.secret debe tener al menos 32 bytes para HS256");
        }

        return bytes;
    }

    @Bean
    JwtEncoder jwtEncoder(@Value("${security.jwt.secret}") String secret) {
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretBytes(secret)));
    }

    @Bean
    JwtDecoder jwtDecoder(@Value("${security.jwt.secret}") String secret) {
        return NimbusJwtDecoder.withSecretKey(
                new javax.crypto.spec.SecretKeySpec(secretBytes(secret), "HmacSHA256")
        ).build();
    }
}
