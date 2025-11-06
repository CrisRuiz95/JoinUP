package com.proyecto.tfg.service;

import com.proyecto.tfg.model.Evento;

import java.util.List;

public interface IEventoService {
    List<Evento> listarTodos();
    Evento buscarPorId(Integer id);
    Evento guardar(Evento evento);
    void eliminar(Integer id);
}
