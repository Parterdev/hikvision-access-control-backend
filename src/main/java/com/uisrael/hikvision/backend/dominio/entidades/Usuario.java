package com.uisrael.hikvision.backend.dominio.entidades;

import com.uisrael.hikvision.backend.dominio.enums.EstadoRegistro;
import com.uisrael.hikvision.backend.dominio.enums.TipoUsuario;
import com.uisrael.hikvision.backend.dominio.excepciones.DominioException;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor

public class Usuario {
	private final Long id;
    private final String identificacion;
    private final String nombreCompleto;
    private final TipoUsuario tipoUsuario;
    private final EstadoRegistro estado;
    private final LocalDateTime fechaRegistro;

    public void validar() {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new DominioException("El nombre completo del usuario es obligatorio.");
        }
        if (tipoUsuario == null) {
            throw new DominioException("El tipo de usuario es obligatorio.");
        }
        if (estado == null) {
            throw new DominioException("El estado del usuario es obligatorio.");
        }
    }
}
