package com.proyecto.tfg.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Usuarios_Eventos")
public class UsuarioEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarios_Eventos")
    private int idUsuariosEventos;

    // Relación con Usuario
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Usuario usuario;

    // Relación con Evento
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;
}
