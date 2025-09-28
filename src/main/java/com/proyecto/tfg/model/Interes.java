package com.proyecto.tfg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Intereses")
public class Interes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_interes")
    private int idInteres;

    @Column(name = "int_v1", length = 45)
    private String v1;

    @Column(name = "int_v2", length = 45)
    private String v2;

    @Column(name = "int_v3", length = 45)
    private String v3;

    @OneToMany(mappedBy = "interes")
    private List<Usuario> usuarios;
}

