package com.uisrael.hikvision.backend.infraestructura.persistencia.jpa;


import com.uisrael.hikvision.backend.dominio.enums.MetodoAutenticacion;
import com.uisrael.hikvision.backend.dominio.enums.ResultadoAcceso;
import com.uisrael.hikvision.backend.dominio.enums.TipoEventoAcceso;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "eventos_acceso",
        indexes = {
                @Index(name = "idx_evento_timestamp", columnList = "timestamp"),
                @Index(name = "idx_evento_usuario", columnList = "usuario_id"),
                @Index(name = "idx_evento_dispositivo", columnList = "dispositivo_id")
        }
)
public class EventoAccesoJpaEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_acceso_id")
    private Long id;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", length = 30, nullable = false)
    private TipoEventoAcceso tipoEvento;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado", length = 30, nullable = false)
    private ResultadoAcceso resultado;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo", length = 30, nullable = false)
    private MetodoAutenticacion metodo;

    @Column(name = "detalle", length = 255, nullable = true)
    private String detalle;

    // Relación: muchos eventos pertenecen a un usuario
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "usuario_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_evento_usuario")
    )
    private UsuarioJpaEntity usuario;

    // Relación: muchos eventos pertenecen a un dispositivo
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "dispositivo_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_evento_dispositivo")
    )
    private DispositivoJpaEntity dispositivo;
}
