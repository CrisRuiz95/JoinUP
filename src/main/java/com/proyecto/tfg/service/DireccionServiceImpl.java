package com.proyecto.tfg.service;


import com.proyecto.tfg.model.Direccion;
import com.proyecto.tfg.service.db.DireccionServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionServiceImpl implements IDireccionService {

    @Autowired
    private DireccionServiceJpa repo;

    @Override
    public List<Direccion> listarTodas() {
        return repo.findAll();
    }

    @Override
    public Direccion buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Direccion guardar(Direccion direccion) {
        return repo.save(direccion);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}