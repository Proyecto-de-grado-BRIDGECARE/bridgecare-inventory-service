package com.bridgecare.inventory.models.entities;

import java.util.ArrayList;
import java.util.List;

import com.bridgecare.common.models.entities.Puente;
import com.bridgecare.common.models.entities.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "observaciones")
    private String observaciones;

    @OneToOne(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Apoyo apoyo;

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Paso> pasos = new ArrayList<>();

    // estribo, detalle, senial, pila
    @OneToOne(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Subestructura subestructura;

    @OneToOne(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Carga carga;

    @OneToOne(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private DatosAdministrativos datosAdministrativos;

    @OneToOne(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private DatosTecnicos datosTecnicos;

    @OneToOne(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private MiembrosInteresados miembrosInteresados;

    @OneToOne(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private PosicionGeografica posicionGeografica;

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Superestructura> superestructuras = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "puente_id", nullable = false, unique = true)
    private Puente puente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Apoyo getApoyo() {
        return apoyo;
    }

    public List<Paso> getPasos() {
        return pasos;
    }

    public Subestructura getSubestructura() {
        return subestructura;
    }

    public Carga getCarga() {
        return carga;
    }

    public DatosAdministrativos getDatosAdministrativos() {
        return datosAdministrativos;
    }

    public DatosTecnicos getDatosTecnicos() {
        return datosTecnicos;
    }

    public MiembrosInteresados getMiembrosInteresados() {
        return miembrosInteresados;
    }

    public PosicionGeografica getPosicionGeografica() {
        return posicionGeografica;
    }

    public List<Superestructura> getSuperestructuras() {
        return superestructuras;
    }

    public Puente getPuente() {
        return puente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setApoyo(Apoyo apoyo) {
        this.apoyo = apoyo;
    }

    public void setPasos(List<Paso> pasos) {
        this.pasos = pasos;
    }

    public void setSubestructura(Subestructura subestructura) {
        this.subestructura = subestructura;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
    }

    public void setDatosAdministrativos(DatosAdministrativos datosAdministrativos) {
        this.datosAdministrativos = datosAdministrativos;
    }

    public void setDatosTecnicos(DatosTecnicos datosTecnicos) {
        this.datosTecnicos = datosTecnicos;
    }

    public void setMiembrosInteresados(MiembrosInteresados miembrosInteresados) {
        this.miembrosInteresados = miembrosInteresados;
    }

    public void setPosicionGeografica(PosicionGeografica posicionGeografica) {
        this.posicionGeografica = posicionGeografica;
    }

    public void setSuperestructuras(List<Superestructura> superestructuras) {
        this.superestructuras = superestructuras;
    }

    public void setPuente(Puente puente) {
        this.puente = puente;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
