package com.uisrael.hikvision.backend.dominio.entidades;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    private Long id;                 // PK local (DB)
    private String employeeNo;        // ISAPI: employeeNo (UNIQUE)
    private String name;             // ISAPI: name
    private String userType;         // ISAPI: userType

    private Boolean localUIRight;    // ISAPI: localUIRight
    private Integer numOfCard;       // ISAPI: numOfCard
    private Integer numOfFace;       // ISAPI: numOfFace

    private ValidityWindow valid;    // ISAPI: Valid{...}
    private List<RightPlanItem> rightPlan; // ISAPI: RightPlan[{doorNo, planTemplateNo}]
}
