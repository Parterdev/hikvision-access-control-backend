package com.uisrael.hikvision.backend.dominio.repositorios;

import com.uisrael.hikvision.backend.dominio.entidades.Dispositivo;

import java.util.List;
import java.util.Optional;

public interface DispositivoRepositorio {
	Dispositivo guardar(Dispositivo dispositivo);

    Optional<Dispositivo> buscarPorId(Long id);

    Optional<Dispositivo> buscarPorCodigo(String codigo);

    Optional<Dispositivo> buscarPorIp(String ip);

    List<Dispositivo> listar();

    void eliminarPorId(Long id);
}
