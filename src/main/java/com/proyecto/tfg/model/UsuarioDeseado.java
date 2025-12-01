package com.proyecto.tfg.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios_deseados")
public class UsuarioDeseado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_deseado")
    private Integer idUsuarioDeseado;

    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;


    public Integer getIdUsuarioDeseado() {
        return idUsuarioDeseado;
    }

    public void setIdUsuarioDeseado(Integer idUsuarioDeseado) {
        this.idUsuarioDeseado = idUsuarioDeseado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }
}
