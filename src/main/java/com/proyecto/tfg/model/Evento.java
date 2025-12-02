package com.proyecto.tfg.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;

    @Column(name = "ev_organ")
    private int idOrgan; // podría ser un Usuario.organizador

    @Column(name = "ev_fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "ev_titulo", length = 80, unique = true)
    private String titulo;
    // URL de la imagen
    @Column(name = "ev_imagen", length = 255)
    private String imagen;

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

    // ✅ Campos de dirección integrados directamente en Evento
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
    private String codigoPostal;

    @Column(name = "dir_provin", length = 45)
    private String provincia;

    @Column(name = "dir_pobla", length = 45)
    private String poblacion;

    @Column(name = "dir_infoExtra", length = 255)
    private String infoExtra;

    @OneToMany(mappedBy = "evento")
    @com.fasterxml.jackson.annotation.JsonIgnore // <--- ¡IMPORTANTE!
    private List<UsuarioEvento> usuarios;

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdOrgan() {
        return idOrgan;
    }

    public void setIdOrgan(int idOrgan) {
        this.idOrgan = idOrgan;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
    }

    public boolean isPro() {
        return pro;
    }

    public void setPro(boolean pro) {
        this.pro = pro;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getNumVia() {
        return numVia;
    }

    public void setNumVia(String numVia) {
        this.numVia = numVia;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getInfoExtra() {
        return infoExtra;
    }

    public void setInfoExtra(String infoExtra) {
        this.infoExtra = infoExtra;
    }

    public List<UsuarioEvento> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEvento> usuarios) {
        this.usuarios = usuarios;
    }
}
