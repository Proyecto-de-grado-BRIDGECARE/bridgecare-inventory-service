package com.bridgecare.inventory.models.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carga")
public class Carga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "longitud_luz_critica")
    private BigDecimal longitudLuzCritica;

    @Column(name = "factor_clasificacion")
    private String factorClasificacion;

    @Column(name = "fuerza_cortante")
    private String fuerzaCortante;

    @Column(name = "momento")
    private String momento;

    @Column(name = "linea_carga_por_rueda")
    private String lineaCargaPorRueda;

    @OneToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

    public BigDecimal getLongitudLuzCritica() {
        return longitudLuzCritica;
    }

    public String getFactorClasificacion() {
        return factorClasificacion;
    }

    public String getFuerzaCortante() {
        return fuerzaCortante;
    }

    public String getMomento() {
        return momento;
    }

    public String getLineaCargaPorRueda() {
        return lineaCargaPorRueda;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLongitudLuzCritica(BigDecimal longitudLuzCritica) {
        this.longitudLuzCritica = longitudLuzCritica;
    }

    public void setFactorClasificacion(String factorClasificacion) {
        this.factorClasificacion = factorClasificacion;
    }

    public void setFuerzaCortante(String fuerzaCortante) {
        this.fuerzaCortante = fuerzaCortante;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public void setLineaCargaPorRueda(String lineaCargaPorRueda) {
        this.lineaCargaPorRueda = lineaCargaPorRueda;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
