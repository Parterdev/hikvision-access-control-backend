package com.uisrael.hikvision.backend.dominio.puertos.repositorio;

import com.uisrael.hikvision.backend.dominio.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorioPort {
	Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorIdentificacion(String identificacion);

    List<Usuario> listar();

    void eliminarPorId(Long id);
}
