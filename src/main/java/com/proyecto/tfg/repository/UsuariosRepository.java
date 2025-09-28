package com.proyecto.tfg.repository;

import com.proyecto.tfg.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UsuariosRepository extends JpaRepository<Usuario,Integer> {
}
