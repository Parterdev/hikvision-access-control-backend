package com.uisrael.hikvision.backend.infraestructura.persistencia.mapeadores;

import com.uisrael.hikvision.backend.dominio.entidades.EventoAcceso;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.DispositivoJpaEntity;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.EventoAccesoJpaEntity;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.UsuarioJpaEntity;

public class EventoAccesoJpaMapper {
	public EventoAccesoJpaEntity aJpa(EventoAcceso dominio, UsuarioJpaEntity usuarioJpa, DispositivoJpaEntity dispositivoJpa) {
        if (dominio == null) return null;

        return EventoAccesoJpaEntity.builder()
                .id(dominio.getId())
                .timestamp(dominio.getTimestamp())
                .tipoEvento(dominio.getTipoEvento())
                .resultado(dominio.getResultado())
                .metodo(dominio.getMetodo())
                .detalle(dominio.getDetalle())
                .usuario(usuarioJpa)
                .dispositivo(dispositivoJpa)
                .build();
    }

    public EventoAcceso aDominio(EventoAccesoJpaEntity jpa) {
        if (jpa == null) return null;

        return EventoAcceso.builder()
                .id(jpa.getId())
                .timestamp(jpa.getTimestamp())
                .tipoEvento(jpa.getTipoEvento())
                .resultado(jpa.getResultado())
                .metodo(jpa.getMetodo())
                .detalle(jpa.getDetalle())
                .usuarioId(jpa.getUsuario() != null ? jpa.getUsuario().getId() : null)
                .dispositivoId(jpa.getDispositivo() != null ? jpa.getDispositivo().getId() : null)
                .build();
    }
}
