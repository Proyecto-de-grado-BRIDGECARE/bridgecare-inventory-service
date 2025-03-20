package com.bridgecare.inventory.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "senial")
public class Senial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carga_maxima")
    private String cargaMaxima;
    
    @Column(name = "velocidad_maxima")
    private String velocidadMaxima;

    @Column(name = "otra")
    private String otra;

    @OneToOne
    @JoinColumn(name = "subestructura_id", nullable = false)
    private Subestructura subestructura;

    public Long getId() {
        return id;
    }

    public String getCargaMaxima() {
        return cargaMaxima;
    }

    public String getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public String getOtra() {
        return otra;
    }

    public Subestructura getSubestructura() {
        return subestructura;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCargaMaxima(String cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public void setVelocidadMaxima(String velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public void setOtra(String otra) {
        this.otra = otra;
    }

    public void setSubestructura(Subestructura subestructura) {
        this.subestructura = subestructura;
    }
}
