package com.uisrael.hikvision.backend.infraestructura.persistencia.mapeadores;

import com.uisrael.hikvision.backend.dominio.entidades.Dispositivo;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.entidades.DispositivoJpaEntity;

public class DispositivoJpaMapper {
	public DispositivoJpaEntity aJpa(Dispositivo dominio) {
        if (dominio == null) return null;

        return DispositivoJpaEntity.builder()
                .id(dominio.getId())
                .codigo(dominio.getCodigo())
                .ip(dominio.getIp())
                .puerto(dominio.getPuerto())
                .modelo(dominio.getModelo())
                .ubicacion(dominio.getUbicacion())
                .estado(dominio.getEstado())
                .build();
    }

    public Dispositivo aDominio(DispositivoJpaEntity jpa) {
        if (jpa == null) return null;

        return Dispositivo.builder()
                .id(jpa.getId())
                .codigo(jpa.getCodigo())
                .ip(jpa.getIp())
                .puerto(jpa.getPuerto())
                .modelo(jpa.getModelo())
                .ubicacion(jpa.getUbicacion())
                .estado(jpa.getEstado())
                .build();
    }
}
