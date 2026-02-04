package com.uisrael.hikvision.backend.infraestructura.persistencia.jpa;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"employee_no"})
})
public class UsuarioJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ISAPI fields
    @Column(name = "employee_no", nullable = false, length = 30)
    private String employeeNo;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "user_type", nullable = false, length = 30)
    private String userType;

    @Column(name = "local_ui_right")
    private Boolean localUIRight;

    @Column(name = "num_of_card")
    private Integer numOfCard;

    @Column(name = "num_of_face")
    private Integer numOfFace;

    // Valid window (plano) - guardado como String para igualar tu VO
    @Column(name = "valid_enable")
    private Boolean validEnable;

    @Column(name = "valid_begin_time", length = 50)
    private String validBeginTime;

    @Column(name = "valid_end_time", length = 50)
    private String validEndTime;

    @Column(name = "valid_time_type", length = 20)
    private String validTimeType;
}
