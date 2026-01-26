package com.uisrael.hikvision.backend.infraestructura.persistencia.adaptadores;

import com.uisrael.hikvision.backend.dominio.entidades.Usuario;
import com.uisrael.hikvision.backend.dominio.puertos.repositorio.UsuarioRepositorioPort;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.repositorios.UsuarioJpaRepository;
import com.uisrael.hikvision.backend.infraestructura.persistencia.mapeadores.UsuarioJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioRepositorioAdapter implements UsuarioRepositorioPort {
	private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioJpaMapper mapper = new UsuarioJpaMapper();

    @Override
    public Usuario guardar(Usuario usuario) {
        var entidad = mapper.aJpa(usuario);
        var guardado = usuarioJpaRepository.save(entidad);
        return mapper.aDominio(guardado);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioJpaRepository.findById(id).map(mapper::aDominio);
    }

    @Override
    public Optional<Usuario> buscarPorIdentificacion(String identificacion) {
        return usuarioJpaRepository.findByIdentificacion(identificacion).map(mapper::aDominio);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioJpaRepository.findAll().stream().map(mapper::aDominio).toList();
    }

    @Override
    public void eliminarPorId(Long id) {
        usuarioJpaRepository.deleteById(id);
    }
}
