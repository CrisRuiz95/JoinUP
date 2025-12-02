package com.proyecto.tfg.repository;

import com.proyecto.tfg.model.UsuarioDeseado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioDeseadoRepository extends JpaRepository<UsuarioDeseado, Integer> {

    // Saber si un usuario ya ha marcado un evento como deseado
    boolean existsByIdClienteAndIdEvento(int idCliente, int idEvento);

    // Eliminar un deseado por usuario y evento
    void deleteByIdClienteAndIdEvento(int idCliente, int idEvento);

    // Obtener todos los eventos deseados de un usuario
    List<UsuarioDeseado> findByIdCliente(int idCliente);

    // Para buscar uno concreto si lo necesitas
    Optional<UsuarioDeseado> findByIdClienteAndIdEvento(int idCliente, int idEvento);
}

