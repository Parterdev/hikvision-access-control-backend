package com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.EventoAccesoJpaEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoAccesoJpaRepository extends JpaRepository<EventoAccesoJpaEntity, Long> {

    List<EventoAccesoJpaEntity> findByUsuario_Id(Long usuarioId);

    List<EventoAccesoJpaEntity> findByDispositivo_Id(Long dispositivoId);

    List<EventoAccesoJpaEntity> findByTimestampBetween(LocalDateTime inicio, LocalDateTime fin);
}
