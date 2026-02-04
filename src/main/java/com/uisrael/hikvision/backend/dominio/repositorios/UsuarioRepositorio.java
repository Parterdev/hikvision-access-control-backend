package com.uisrael.hikvision.backend.dominio.repositorios;

import com.uisrael.hikvision.backend.dominio.entidades.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> listarTodos();
    void eliminar(Long id);
}