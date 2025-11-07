package com.proyecto.tfg.service;

import com.proyecto.tfg.exception.UserAlreadyExistsException;
import com.proyecto.tfg.model.Evento;
import com.proyecto.tfg.model.Usuario;
import com.proyecto.tfg.service.db.EventoServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements IEventoService {

    @Autowired
    private EventoServiceJpa repo;

    @Override
    public List<Evento> listAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Evento> fetchEvento(int idEvemto) {
        return repo.findById(idEvemto);
    }

    @Override
    public Evento save(Evento event) {
        List<Evento> existingEvents = repo.findByTituloContainingIgnoreCase(event.getTitulo());

        if (!existingEvents.isEmpty()) {
            // Aquí lanzamos una excepción personalizada con el mensaje claro
            throw new UserAlreadyExistsException("No puedes poner un título repetido: " + event.getTitulo());
        }
        // Si no existe, se crea normalmente
        Evento newEvent = createNewEvent(event);
        return repo.save(newEvent);
    }
    private Evento createNewEvent(Evento evento) {
        Evento newEvento = new Evento();

        // Datos básicos
        newEvento.setIdOrgan(evento.getIdOrgan()); // ID del organizador
        newEvento.setFecha(evento.getFecha());
        newEvento.setTitulo(evento.getTitulo());
        newEvento.setDescripcion(evento.getDescripcion());
        newEvento.setMaxParticipantes(evento.getMaxParticipantes());
        newEvento.setPro(evento.isPro());
        newEvento.setPrecio(evento.getPrecio());

        // Tags
        newEvento.setTag1(evento.getTag1());
        newEvento.setTag2(evento.getTag2());
        newEvento.setTag3(evento.getTag3());

        // Dirección
        newEvento.setTipoVia(evento.getTipoVia());
        newEvento.setVia(evento.getVia());
        newEvento.setNumVia(evento.getNumVia());
        newEvento.setPiso(evento.getPiso());
        newEvento.setPuerta(evento.getPuerta());
        newEvento.setCodigoPostal(evento.getCodigoPostal());
        newEvento.setProvincia(evento.getProvincia());
        newEvento.setPoblacion(evento.getPoblacion());
        newEvento.setInfoExtra(evento.getInfoExtra());

        // Imagen (si existe)
        newEvento.setImagen(evento.getImagen());

        // Inicializamos la lista de usuarios vacía
        newEvento.setUsuarios(new ArrayList<>());

        return newEvento;
    }


    @Override
    public boolean delete(Integer id) {
        Optional<Evento> eventoOpt = repo.findById(id);
        if (eventoOpt.isPresent()) {
            repo.delete(eventoOpt.get());
            return true; // Eliminación exitosa
        }
        return false; // Usuario no encontrado
    }

    @Override
    public List<Evento> findByTitulo(String titulo) {
        return repo.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public Evento updateEvento(int idEvento, Evento updatedEvento) {
        // Buscamos el evento por ID usando Optional
        Optional<Evento> optionalEvento = repo.findById(idEvento);

        if (optionalEvento.isPresent()) {
            Evento existingEvento = optionalEvento.get();

            // Actualizamos los campos
            existingEvento.setTitulo(updatedEvento.getTitulo());
            existingEvento.setDescripcion(updatedEvento.getDescripcion());
            existingEvento.setFecha(updatedEvento.getFecha());
            existingEvento.setMaxParticipantes(updatedEvento.getMaxParticipantes());
            existingEvento.setPro(updatedEvento.isPro());
            existingEvento.setPrecio(updatedEvento.getPrecio());
            existingEvento.setImagen(updatedEvento.getImagen());

            existingEvento.setTag1(updatedEvento.getTag1());
            existingEvento.setTag2(updatedEvento.getTag2());
            existingEvento.setTag3(updatedEvento.getTag3());

            existingEvento.setTipoVia(updatedEvento.getTipoVia());
            existingEvento.setVia(updatedEvento.getVia());
            existingEvento.setNumVia(updatedEvento.getNumVia());
            existingEvento.setPiso(updatedEvento.getPiso());
            existingEvento.setPuerta(updatedEvento.getPuerta());
            existingEvento.setCodigoPostal(updatedEvento.getCodigoPostal());
            existingEvento.setProvincia(updatedEvento.getProvincia());
            existingEvento.setPoblacion(updatedEvento.getPoblacion());
            existingEvento.setInfoExtra(updatedEvento.getInfoExtra());

            // Guardamos el evento actualizado
            return repo.save(existingEvento);
        } else {
            // Opcional: lanzar excepción si no existe
            throw new RuntimeException("Evento con ID " + idEvento + " no encontrado.");
        }
    }


    // ===== Métodos personalizados =====


}
