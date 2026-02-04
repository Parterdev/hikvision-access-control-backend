package com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios;

import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.UsuarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioJpaEntity, Long> {
}
