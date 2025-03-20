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
@Table(name = "posicion_geografica")
public class PosicionGeografica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "latitud")
    private BigDecimal latitud;

    @Column(name = "longitud")
    private BigDecimal longitud;

    @Column(name = "altitud")
    private BigDecimal altitud;

    @Column(name = "coeficiente_aceleracion_sismica")
    private String coeficienteAceleracionSismica;

    @Column(name = "paso_cauce")
    private Boolean pasoCauce;

    @Column(name = "existe_variante")
    private Boolean existeVariante;

    @Column(name = "longitud_variante")
    private BigDecimal longitudVariante;

    @Column(name = "estado")
    private String estado;

    @OneToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public BigDecimal getAltitud() {
        return altitud;
    }

    public String getCoeficienteAceleracionSismica() {
        return coeficienteAceleracionSismica;
    }

    public Boolean getPasoCauce() {
        return pasoCauce;
    }

    public Boolean getExisteVariante() {
        return existeVariante;
    }

    public BigDecimal getLongitudVariante() {
        return longitudVariante;
    }

    public String getEstado() {
        return estado;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public void setAltitud(BigDecimal altitud) {
        this.altitud = altitud;
    }

    public void setCoeficienteAceleracionSismica(String coeficienteAceleracionSismica) {
        this.coeficienteAceleracionSismica = coeficienteAceleracionSismica;
    }

    public void setPasoCauce(Boolean pasoCauce) {
        this.pasoCauce = pasoCauce;
    }

    public void setExisteVariante(Boolean existeVariante) {
        this.existeVariante = existeVariante;
    }

    public void setLongitudVariante(BigDecimal longitudVariante) {
        this.longitudVariante = longitudVariante;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
