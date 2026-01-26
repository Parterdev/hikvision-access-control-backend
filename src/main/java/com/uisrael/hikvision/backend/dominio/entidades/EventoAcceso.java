package com.uisrael.hikvision.backend.dominio.entidades;

import com.uisrael.hikvision.backend.dominio.enums.MetodoAutenticacion;
import com.uisrael.hikvision.backend.dominio.enums.ResultadoAcceso;
import com.uisrael.hikvision.backend.dominio.enums.TipoEventoAcceso;
import com.uisrael.hikvision.backend.dominio.excepciones.DominioException;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor

public class EventoAcceso {
	private final Long id;
    private final LocalDateTime timestamp;
    private final TipoEventoAcceso tipoEvento;
    private final ResultadoAcceso resultado;
    private final MetodoAutenticacion metodo;
    private final String detalle;

    // Referencias por ID (DDD-friendly)
    private final Long usuarioId;
    private final Long dispositivoId;

    public void validar() {
        if (timestamp == null) {
            throw new DominioException("El timestamp del evento es obligatorio.");
        }
        if (tipoEvento == null) {
            throw new DominioException("El tipo de evento es obligatorio.");
        }
        if (resultado == null) {
            throw new DominioException("El resultado del acceso es obligatorio.");
        }
        if (metodo == null) {
            throw new DominioException("El método de autenticación es obligatorio.");
        }
        if (usuarioId == null) {
            throw new DominioException("El usuarioId del evento es obligatorio.");
        }
        if (dispositivoId == null) {
            throw new DominioException("El dispositivoId del evento es obligatorio.");
        }
    }
}
