package com.proyecto.tfg.service;

import com.proyecto.tfg.model.UsuarioEvento;

public interface IUsuarioEventoService {

    UsuarioEvento unirseAEvento(int idUsuario, int idEvento);
    boolean desunirseDeEvento(int idUsuario, int idEvento);
    boolean estaInscrito(int idUsuario, int idEvento);
}