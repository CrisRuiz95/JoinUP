package com.proyecto.tfg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
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

    @Column(name = "d_email", nullable = false, unique = true, length = 45)
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
}
