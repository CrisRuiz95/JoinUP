package com.proyecto.tfg.service;

import com.proyecto.tfg.model.Evento;
import com.proyecto.tfg.service.db.EventoServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServiceImpl implements IEventoService {

    @Autowired
    private EventoServiceJpa repo;

    @Override
    public List<Evento> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Evento buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Evento guardar(Evento evento) {
        return repo.save(evento);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
