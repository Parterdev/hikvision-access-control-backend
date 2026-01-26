package com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.entidades;

import com.uisrael.hikvision.backend.dominio.enums.EstadoRegistro;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "dispositivos",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_dispositivo_codigo", columnNames = {"codigo"})
        }
)

public class DispositivoJpaEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dispositivo_id")
    private Long id;

    @Column(name = "codigo", length = 80, nullable = false)
    private String codigo;

    @Column(name = "ip", length = 45, nullable = false)
    private String ip;

    @Column(name = "puerto", nullable = false)
    private Integer puerto;

    @Column(name = "modelo", length = 80, nullable = true)
    private String modelo;

    @Column(name = "ubicacion", length = 120, nullable = true)
    private String ubicacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 30, nullable = false)
    private EstadoRegistro estado;
}	
