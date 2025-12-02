package com.proyecto.tfg.model;

import jakarta.persistence.*;
import lombok.Data;


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

    public int getIdUsuariosEventos() {
        return idUsuariosEventos;
    }

    public void setIdUsuariosEventos(int idUsuariosEventos) {
        this.idUsuariosEventos = idUsuariosEventos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}

