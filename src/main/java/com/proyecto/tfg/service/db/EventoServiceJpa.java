package com.proyecto.tfg.service.db;

import com.proyecto.tfg.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoServiceJpa extends JpaRepository<Evento, Integer> {
}
