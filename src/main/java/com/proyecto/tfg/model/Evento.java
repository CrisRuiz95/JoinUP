package com.proyecto.tfg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private int idEvento;

    @Column(name = "id_organ")
    private int idOrgan; // podría ser un Usuario.organizador

    @Column(name = "ev_fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "ev_titulo", length = 80)
    private String titulo;

    @Column(name = "ev_desc", length = 200)
    private String descripcion;

    @Column(name = "ev_maxPartic")
    private int maxParticipantes;

    @Column(name = "ev_pro")
    private boolean pro;

    @Column(name = "ev_tag1", length = 45)
    private String tag1;

    @Column(name = "ev_tag2", length = 45)
    private String tag2;

    @Column(name = "ev_tag3", length = 45)
    private String tag3;

    @Column(name = "ev_precio", length = 45)
    private String precio;

    @OneToMany(mappedBy = "evento")
    private List<UsuarioEvento> usuarios;
}

