package com.uisrael.hikvision.backend.infraestructura.persistencia.mapeadores;

import com.uisrael.hikvision.backend.dominio.entidades.Usuario;
import com.uisrael.hikvision.backend.dominio.entidades.ValidityWindow;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.UsuarioJpaEntity;

public class UsuarioJpaMapper {

    public UsuarioJpaEntity toEntity(Usuario dominio) {
        if (dominio == null) return null;

        UsuarioJpaEntity entity = new UsuarioJpaEntity();
        entity.setId(dominio.getId());

        // ISAPI fields
        entity.setEmployeeNo(dominio.getEmployeeNo());
        entity.setName(dominio.getName());
        entity.setUserType(dominio.getUserType());

        entity.setLocalUIRight(dominio.getLocalUIRight());
        entity.setNumOfCard(dominio.getNumOfCard());
        entity.setNumOfFace(dominio.getNumOfFace());

        // Valid window (guardamos plano)
        if (dominio.getValid() != null) {
            entity.setValidEnable(dominio.getValid().getEnable());
            entity.setValidBeginTime(dominio.getValid().getBeginTime());
            entity.setValidEndTime(dominio.getValid().getEndTime());
            entity.setValidTimeType(dominio.getValid().getTimeType());
        }

        // RightPlan: por ahora NO lo persistimos (Sprint 1)
        // Lo agregaremos cuando definamos tabla o JSON column.
        return entity;
    }

    public Usuario toDomain(UsuarioJpaEntity entity) {
        if (entity == null) return null;

        ValidityWindow valid = ValidityWindow.builder()
                .enable(entity.getValidEnable())
                .beginTime(entity.getValidBeginTime())
                .endTime(entity.getValidEndTime())
                .timeType(entity.getValidTimeType())
                .build();

        return Usuario.builder()
                .id(entity.getId())

                // ISAPI fields
                .employeeNo(entity.getEmployeeNo())
                .name(entity.getName())
                .userType(entity.getUserType())

                .localUIRight(entity.getLocalUIRight())
                .numOfCard(entity.getNumOfCard())
                .numOfFace(entity.getNumOfFace())

                .valid(valid)
                .rightPlan(null) // luego lo persistimos bien
                .build();
    }
}
