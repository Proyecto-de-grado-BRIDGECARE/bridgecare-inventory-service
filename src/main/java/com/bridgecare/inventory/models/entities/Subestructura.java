package com.bridgecare.inventory.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "subestructura")
public class Subestructura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "subestructura", cascade = CascadeType.ALL, orphanRemoval = true)
    private Estribo estribo;

    @OneToOne(mappedBy = "subestructura", cascade = CascadeType.ALL, orphanRemoval = true)
    private Detalle detalle;

    @OneToOne(mappedBy = "subestructura", cascade = CascadeType.ALL, orphanRemoval = true)
    private Senial senial;

    @OneToOne(mappedBy = "subestructura", cascade = CascadeType.ALL, orphanRemoval = true)
    private Pila pila;

    @OneToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

    public Estribo getEstribo() {
        return estribo;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public Senial getSenial() {
        return senial;
    }

    public Pila getPila() {
        return pila;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstribo(Estribo estribo) {
        this.estribo = estribo;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    public void setSenial(Senial senial) {
        this.senial = senial;
    }

    public void setPila(Pila pila) {
        this.pila = pila;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
