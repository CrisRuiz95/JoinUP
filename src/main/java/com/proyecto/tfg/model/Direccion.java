package com.proyecto.tfg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private int idDireccion;

    @Column(name = "dir_tipoVia", length = 45)
    private String tipoVia;

    @Column(name = "dir_via", length = 45)
    private String via;

    @Column(name = "dir_numVia", length = 45)
    private String numVia;

    @Column(name = "dir_piso", length = 45)
    private String piso;

    @Column(name = "dir_puerta", length = 45)
    private String puerta;

    @Column(name = "dir_codigo", length = 45)
    private String codigo;

    @Column(name = "dir_provin", length = 45)
    private String provincia;

    @Column(name = "dir_pobla", length = 45)
    private String poblacion;

    @Column(name = "dir_infoExtra", length = 250)
    private String infoExtra;

    @OneToMany(mappedBy = "direccion")
    private List<Usuario> usuarios;
}
