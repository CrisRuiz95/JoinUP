package com.proyecto.tfg.repository;



import com.proyecto.tfg.model.Evento;
import com.proyecto.tfg.model.Usuario;
import com.proyecto.tfg.model.UsuarioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioEventoRepository extends JpaRepository<UsuarioEvento, Integer> {
    Optional<UsuarioEvento> findByUsuarioAndEvento(Usuario usuario, Evento evento);
}