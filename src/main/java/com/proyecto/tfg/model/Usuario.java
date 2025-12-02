package com.proyecto.tfg.model;

import jakarta.persistence.*;
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

    @Column(name = "d_password", nullable = false, length = 90)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "d_rol", nullable = false)
    private Rol rol; // ENUM de roles

    // ✅ Campos de dirección directamente en Usuario
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

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_interes")
    private Interes interes;

    @OneToMany(mappedBy = "usuario")
    @com.fasterxml.jackson.annotation.JsonIgnore // <--- ¡IMPORTANTE!
    private List<UsuarioEvento> eventos;

    // Getters y setters
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
