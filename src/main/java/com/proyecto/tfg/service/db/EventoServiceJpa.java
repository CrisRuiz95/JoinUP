package com.proyecto.tfg.service.db;

import com.proyecto.tfg.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoServiceJpa extends JpaRepository<Evento, Integer> {
    List<Evento> findByTituloContainingIgnoreCase(String titulo); // búsqueda parcial, case-insensitive
    List<Evento> findByTag1OrTag2OrTag3(String tag1, String tag2, String tag3);


}
