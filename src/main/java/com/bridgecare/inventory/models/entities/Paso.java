package com.bridgecare.inventory.models.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paso")
public class Paso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "tipo_paso")
    private String tipoPaso;

    @Column(name = "primero")
    private Boolean primero;

    @Column(name = "sup_inf")
    private String supInf;

    @Column(name = "galibo_i")
    private BigDecimal galiboI;

    @Column(name = "galibo_im")
    private BigDecimal galiboIm;

    @Column(name = "galibo_dm")
    private BigDecimal galiboDm;

    @Column(name = "galibo_d")
    private BigDecimal galiboD;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getTipoPaso() {
        return tipoPaso;
    }

    public Boolean getPrimero() {
        return primero;
    }

    public String getSupInf() {
        return supInf;
    }

    public BigDecimal getGaliboI() {
        return galiboI;
    }

    public BigDecimal getGaliboIm() {
        return galiboIm;
    }

    public BigDecimal getGaliboDm() {
        return galiboDm;
    }

    public BigDecimal getGaliboD() {
        return galiboD;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setTipoPaso(String tipoPaso) {
        this.tipoPaso = tipoPaso;
    }

    public void setPrimero(Boolean primero) {
        this.primero = primero;
    }

    public void setSupInf(String supInf) {
        this.supInf = supInf;
    }

    public void setGaliboI(BigDecimal galiboI) {
        this.galiboI = galiboI;
    }

    public void setGaliboIm(BigDecimal galiboIm) {
        this.galiboIm = galiboIm;
    }

    public void setGaliboDm(BigDecimal galiboDm) {
        this.galiboDm = galiboDm;
    }

    public void setGaliboD(BigDecimal galiboD) {
        this.galiboD = galiboD;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
