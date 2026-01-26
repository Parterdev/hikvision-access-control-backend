package com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.repositorios;

import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.entidades.DispositivoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DispositivoJpaRepository extends JpaRepository<DispositivoJpaEntity, Long> {
    Optional<DispositivoJpaEntity> findByCodigo(String codigo);
    Optional<DispositivoJpaEntity> findByIp(String ip);
}