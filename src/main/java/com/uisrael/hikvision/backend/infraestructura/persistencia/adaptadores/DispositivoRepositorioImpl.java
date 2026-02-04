package com.uisrael.hikvision.backend.infraestructura.persistencia.adaptadores;

import com.uisrael.hikvision.backend.dominio.entidades.Dispositivo;
import com.uisrael.hikvision.backend.dominio.repositorios.DispositivoRepositorio;
import com.uisrael.hikvision.backend.infraestructura.persistencia.mapeadores.DispositivoJpaMapper;
import com.uisrael.hikvision.backend.infraestructura.persistencia.repositorios.DispositivoJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DispositivoRepositorioImpl implements DispositivoRepositorio {
	private final DispositivoJpaRepository dispositivoJpaRepository;
    private final DispositivoJpaMapper mapper = new DispositivoJpaMapper();

    @Override
    public Dispositivo guardar(Dispositivo dispositivo) {
        var entidad = mapper.aJpa(dispositivo);
        var guardado = dispositivoJpaRepository.save(entidad);
        return mapper.aDominio(guardado);
    }

    @Override
    public Optional<Dispositivo> buscarPorId(Long id) {
        return dispositivoJpaRepository.findById(id).map(mapper::aDominio);
    }

    @Override
    public Optional<Dispositivo> buscarPorCodigo(String codigo) {
        return dispositivoJpaRepository.findByCodigo(codigo).map(mapper::aDominio);
    }

    @Override
    public Optional<Dispositivo> buscarPorIp(String ip) {
        return dispositivoJpaRepository.findByIp(ip).map(mapper::aDominio);
    }

    @Override
    public List<Dispositivo> listar() {
        return dispositivoJpaRepository.findAll().stream().map(mapper::aDominio).toList();
    }

    @Override
    public void eliminarPorId(Long id) {
        dispositivoJpaRepository.deleteById(id);
    }
}
