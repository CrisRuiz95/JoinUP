package com.proyecto.tfg.repository;

import com.proyecto.tfg.model.Interes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InteresesRepository extends JpaRepository<Interes,Integer> {
}
