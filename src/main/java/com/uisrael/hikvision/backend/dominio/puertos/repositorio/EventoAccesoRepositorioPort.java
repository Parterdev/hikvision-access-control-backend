package com.uisrael.hikvision.backend.dominio.puertos.repositorio;

import com.uisrael.hikvision.backend.dominio.entidades.EventoAcceso;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventoAccesoRepositorioPort {
	EventoAcceso guardar(EventoAcceso evento);

    Optional<EventoAcceso> buscarPorId(Long id);

    List<EventoAcceso> listarPorUsuarioId(Long usuarioId);

    List<EventoAcceso> listarPorDispositivoId(Long dispositivoId);

    List<EventoAcceso> listarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin);
}
