package com.uisrael.hikvision.backend.infraestructura.persistencia.mapeadores;

import com.uisrael.hikvision.backend.dominio.entidades.Usuario;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.entidades.UsuarioJpaEntity;

public class UsuarioJpaMapper {
	public UsuarioJpaEntity aJpa(Usuario dominio) {
        if (dominio == null) return null;

        return UsuarioJpaEntity.builder()
                .id(dominio.getId())
                .identificacion(dominio.getIdentificacion())
                .nombreCompleto(dominio.getNombreCompleto())
                .tipoUsuario(dominio.getTipoUsuario())
                .estado(dominio.getEstado())
                .fechaRegistro(dominio.getFechaRegistro())
                .build();
    }

    public Usuario aDominio(UsuarioJpaEntity jpa) {
        if (jpa == null) return null;

        return Usuario.builder()
                .id(jpa.getId())
                .identificacion(jpa.getIdentificacion())
                .nombreCompleto(jpa.getNombreCompleto())
                .tipoUsuario(jpa.getTipoUsuario())
                .estado(jpa.getEstado())
                .fechaRegistro(jpa.getFechaRegistro())
                .build();
    }
}
