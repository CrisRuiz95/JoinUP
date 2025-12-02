package com.proyecto.tfg.repository;

import com.proyecto.tfg.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventosRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findByTituloContainingIgnoreCase(String titulo);

    List<Evento> findByTag1ContainingIgnoreCaseOrTag2ContainingIgnoreCaseOrTag3ContainingIgnoreCase(
            String tag1, String tag2, String tag3
    );
}
