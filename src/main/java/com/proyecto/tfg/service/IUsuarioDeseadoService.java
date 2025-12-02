package com.proyecto.tfg.service;

public interface IUsuarioDeseadoService {

    String toggleDeseado(int idCliente, int idEvento);

    boolean esDeseado(int idCliente, int idEvento);

    void eliminarDeseado(int idCliente, int idEvento);
}
