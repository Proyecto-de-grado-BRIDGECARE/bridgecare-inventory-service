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
@Table(name = "pila")
public class Pila {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private Integer tipo;
    
    @Column(name = "material")
    private Integer material;

    @Column(name = "tipo_cimentacion")
    private Integer tipoCimentacion;

    @OneToOne
    @JoinColumn(name = "subestructura_id", nullable = false)
    private Subestructura subestructura;

    public Long getId() {
        return id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public Integer getMaterial() {
        return material;
    }

    public Integer getTipoCimentacion() {
        return tipoCimentacion;
    }

    public Subestructura getSubestructura() {
        return subestructura;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public void setTipoCimentacion(Integer tipoCimentacion) {
        this.tipoCimentacion = tipoCimentacion;
    }

    public void setSubestructura(Subestructura subestructura) {
        this.subestructura = subestructura;
    }
}
