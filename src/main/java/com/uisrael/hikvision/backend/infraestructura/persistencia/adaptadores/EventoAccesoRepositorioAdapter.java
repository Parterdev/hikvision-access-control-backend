package com.uisrael.hikvision.backend.infraestructura.persistencia.adaptadores;

import com.uisrael.hikvision.backend.dominio.entidades.EventoAcceso;
import com.uisrael.hikvision.backend.dominio.puertos.repositorio.EventoAccesoRepositorioPort;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.repositorios.DispositivoJpaRepository;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.repositorios.EventoAccesoJpaRepository;
import com.uisrael.hikvision.backend.infraestructura.persistencia.jpa.repositorios.UsuarioJpaRepository;
import com.uisrael.hikvision.backend.infraestructura.persistencia.mapeadores.EventoAccesoJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventoAccesoRepositorioAdapter implements EventoAccesoRepositorioPort {
	private final EventoAccesoJpaRepository eventoJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final DispositivoJpaRepository dispositivoJpaRepository;

    private final EventoAccesoJpaMapper mapper = new EventoAccesoJpaMapper();

    @Override
    public EventoAcceso guardar(EventoAcceso evento) {
        // Cargar entidades relacionadas (FK)
        var usuario = usuarioJpaRepository.findById(evento.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe para eventoAcceso"));
        var dispositivo = dispositivoJpaRepository.findById(evento.getDispositivoId())
                .orElseThrow(() -> new IllegalArgumentException("Dispositivo no existe para eventoAcceso"));

        var entidad = mapper.aJpa(evento, usuario, dispositivo);
        var guardado = eventoJpaRepository.save(entidad);
        return mapper.aDominio(guardado);
    }

    @Override
    public Optional<EventoAcceso> buscarPorId(Long id) {
        return eventoJpaRepository.findById(id).map(mapper::aDominio);
    }

    @Override
    public List<EventoAcceso> listarPorUsuarioId(Long usuarioId) {
        return eventoJpaRepository.findByUsuario_Id(usuarioId).stream().map(mapper::aDominio).toList();
    }

    @Override
    public List<EventoAcceso> listarPorDispositivoId(Long dispositivoId) {
        return eventoJpaRepository.findByDispositivo_Id(dispositivoId).stream().map(mapper::aDominio).toList();
    }

    @Override
    public List<EventoAcceso> listarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return eventoJpaRepository.findByTimestampBetween(inicio, fin).stream().map(mapper::aDominio).toList();
    }
}
