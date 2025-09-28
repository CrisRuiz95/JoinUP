package com.proyecto.tfg.repository;

import com.proyecto.tfg.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EventosRepository extends JpaRepository<Evento,Integer> {
}
