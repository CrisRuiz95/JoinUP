package com.proyecto.tfg.service.db;

import com.proyecto.tfg.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionServiceJpa extends JpaRepository<Direccion, Integer> {
}
