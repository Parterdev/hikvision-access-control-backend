package com.uisrael.hikvision.backend.dominio.entidades;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidityWindow {
    private Boolean enable;
    private String beginTime; // ISAPI: "2000-01-01T00:00:00" (o con -05:00)
    private String endTime;   // ISAPI: "2037-12-31T23:59:59"
    private String timeType;  // ISAPI: "local"
}
