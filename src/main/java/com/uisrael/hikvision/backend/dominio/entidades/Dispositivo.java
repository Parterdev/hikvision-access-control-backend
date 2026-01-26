package com.uisrael.hikvision.backend.dominio.entidades;

import com.uisrael.hikvision.backend.dominio.enums.EstadoRegistro;
import com.uisrael.hikvision.backend.dominio.excepciones.DominioException;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor

public class Dispositivo {
	private final Long id;
    private final String codigo;
    private final String ip;
    private final Integer puerto;
    private final String modelo;
    private final String ubicacion;
    private final EstadoRegistro estado;

    public void validar() {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new DominioException("El código del dispositivo es obligatorio.");
        }
        if (ip == null || ip.trim().isEmpty()) {
            throw new DominioException("La IP del dispositivo es obligatoria.");
        }
        if (puerto == null || puerto < 1 || puerto > 65535) {
            throw new DominioException("El puerto del dispositivo debe estar entre 1 y 65535.");
        }
        if (estado == null) {
            throw new DominioException("El estado del dispositivo es obligatorio.");
        }
    }
}
