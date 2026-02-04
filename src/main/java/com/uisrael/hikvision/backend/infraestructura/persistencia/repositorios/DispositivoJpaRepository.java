package com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.DispositivoJpaEntity;

import java.util.Optional;


public interface DispositivoJpaRepository extends JpaRepository<DispositivoJpaEntity, Long> {
    Optional<DispositivoJpaEntity> findByCodigo(String codigo);
    Optional<DispositivoJpaEntity> findByIp(String ip);
}