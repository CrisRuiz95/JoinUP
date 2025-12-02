package com.proyecto.tfg.service.impl;

import com.proyecto.tfg.model.Evento;
import com.proyecto.tfg.model.Usuario;
import com.proyecto.tfg.model.UsuarioEvento;
import com.proyecto.tfg.repository.EventosRepository;
import com.proyecto.tfg.repository.UsuarioEventoRepository;
import com.proyecto.tfg.repository.UsuariosRepository;
import com.proyecto.tfg.service.IUsuarioEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioEventoServiceImpl implements IUsuarioEventoService {

    @Autowired
    private UsuarioEventoRepository repo;

    @Autowired
    private UsuariosRepository usuarioRepo;

    @Autowired
    private EventosRepository eventoRepo;

    @Override
    @Transactional
    public UsuarioEvento unirseAEvento(int idUsuario, int idEvento) {
        // 1. Buscamos las entidades
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        Evento evento = eventoRepo.findById(idEvento)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + idEvento));

        // 2. Validamos si ya existe (Usando QBE a través de estaInscrito)
        if (estaInscrito(idUsuario, idEvento)) {
            throw new RuntimeException("El usuario ya está inscrito en este evento.");
        }

        // 3. Validamos aforo
        if (evento.getMaxParticipantes() > 0) {
            long inscritos = evento.getUsuarios() != null ? evento.getUsuarios().size() : 0;
            if (inscritos >= evento.getMaxParticipantes()) {
                throw new RuntimeException("El evento ha completado su aforo.");
            }
        }

        // 4. Guardamos
        return repo.save(createInscripcion(usuario, evento));
    }

    private UsuarioEvento createInscripcion(Usuario usuario, Evento evento) {
        UsuarioEvento inscripcion = new UsuarioEvento();
        // Nota: Asume que tienes los setters en UsuarioEvento (por Lombok o manuales)
        inscripcion.setUsuario(usuario);
        inscripcion.setEvento(evento);
        return inscripcion;
    }


    @Override
    @Transactional
    public boolean desunirseDeEvento(int idUsuario, int idEvento) {
        // 1. Obtener Entidades (se mantiene)
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        Evento evento = eventoRepo.findById(idEvento)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + idEvento));

        Optional<UsuarioEvento> inscripcion = repo.findByUsuarioAndEvento(usuario, evento);

        if (inscripcion.isPresent()) {
            repo.delete(inscripcion.get());
            return true;
        }

        return false;
    }

    @Override
    public boolean estaInscrito(int idUsuario, int idEvento) {
        Usuario usuario = usuarioRepo.findById(idUsuario).orElse(null);
        Evento evento = eventoRepo.findById(idEvento).orElse(null);

        if (usuario != null && evento != null) {
            // Creamos el molde de búsqueda (probe)
            UsuarioEvento probe = new UsuarioEvento();
            probe.setUsuario(usuario);
            probe.setEvento(evento);

            // Usamos el método genérico exists() con el molde QBE.
            return repo.exists(Example.of(probe));
        }
        return false;
    }
}