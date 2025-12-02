package com.proyecto.tfg.service;

import com.proyecto.tfg.model.Evento;
import com.proyecto.tfg.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IEventoService {

    // List all events
    List<Evento> listAll();

    // Find event by ID
    Optional<Evento> fetchEvento(int idEvento);

    // Save or update an event
    Evento save(Evento event);

    // Delete an event by ID
    boolean delete(Integer id);
    List<Evento> findByTitulo(String titulo);
    Evento updateEvento(int idEvento, Evento updatedEvento);
    public List<Usuario> getParticipantes(int idEvento);
    List<Evento> findByTags(String tag);
}
