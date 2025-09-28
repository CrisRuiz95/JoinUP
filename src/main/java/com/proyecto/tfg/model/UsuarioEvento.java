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

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;
}

