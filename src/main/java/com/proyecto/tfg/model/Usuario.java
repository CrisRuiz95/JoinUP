package com.proyecto.tfg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    @Column(name = "d_nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "d_ap1", length = 45)
    private String ap1;

    @Column(name = "d_ap2", length = 45)
    private String ap2;


    @Column(name = "d_email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "d_password", nullable = false, length = 45)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "d_rol", nullable = false)
    private Rol rol; // ENUM de roles

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;

    @ManyToOne
    @JoinColumn(name = "id_interes")
    private Interes interes;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioEvento> eventos;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Interes getInteres() {
        return interes;
    }

    public void setInteres(Interes interes) {
        this.interes = interes;
    }

    public List<UsuarioEvento> getEventos() {
        return eventos;
    }

    public void setEventos(List<UsuarioEvento> eventos) {
        this.eventos = eventos;
    }
}

