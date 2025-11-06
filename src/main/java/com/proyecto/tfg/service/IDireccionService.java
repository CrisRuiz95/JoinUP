package com.proyecto.tfg.service;

import com.proyecto.tfg.model.Direccion;

import java.util.List;

public interface IDireccionService {
    List<Direccion> listarTodas();
    Direccion buscarPorId(Integer id);
    Direccion guardar(Direccion direccion);
    void eliminar(Integer id);
}
