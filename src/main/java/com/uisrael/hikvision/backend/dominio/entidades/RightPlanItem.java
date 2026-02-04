package com.uisrael.hikvision.backend.dominio.entidades;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RightPlanItem {
    private Integer doorNo;
    private String planTemplateNo;
}
