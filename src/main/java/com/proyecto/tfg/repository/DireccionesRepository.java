package com.proyecto.tfg.repository;

import com.proyecto.tfg.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionesRepository extends JpaRepository<Direccion,Integer> {
}
