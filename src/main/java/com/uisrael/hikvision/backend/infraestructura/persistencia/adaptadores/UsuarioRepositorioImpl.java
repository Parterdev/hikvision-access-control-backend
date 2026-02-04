package com.uisrael.hikvision.backend.infraestructura.persistencia.adaptadores;

import com.uisrael.hikvision.backend.dominio.entidades.Usuario;
import com.uisrael.hikvision.backend.dominio.repositorios.UsuarioRepositorio;
import com.uisrael.hikvision.backend.infraestructura.persistencia.mapeadores.UsuarioJpaMapper;
import com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios.UsuarioJpaRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioRepositorioImpl implements UsuarioRepositorio {

    private final UsuarioJpaRepository jpaRepository;
    private final UsuarioJpaMapper mapper;

    public UsuarioRepositorioImpl(UsuarioJpaRepository jpaRepository, UsuarioJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        var entity = mapper.toEntity(usuario);
        var saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }
}
