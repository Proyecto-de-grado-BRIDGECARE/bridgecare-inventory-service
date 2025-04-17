package com.bridgecare.inventory.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "superestructura")
public class Superestructura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "disenio_tipo")
    private Boolean disenioTipo;

    @Column(name = "tipo_estructuracion_transversal")
    private Integer tipoEstructuracionTransversal;

    @Column(name = "tipo_estructuracion_longitudinal")
    private Integer tipoEstructuracionLongitudinal;

    @Column(name = "material")
    private Integer material;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Boolean getDisenioTipo() {
        return disenioTipo;
    }

    public Integer getTipoEstructuracionTransversal() {
        return tipoEstructuracionTransversal;
    }

    public Integer getTipoEstructuracionLongitudinal() {
        return tipoEstructuracionLongitudinal;
    }

    public Integer getMaterial() {
        return material;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDisenioTipo(Boolean disenioTipo) {
        this.disenioTipo = disenioTipo;
    }

    public void setTipoEstructuracionTransversal(Integer tipoEstructuracionTransversal) {
        this.tipoEstructuracionTransversal = tipoEstructuracionTransversal;
    }

    public void setTipoEstructuracionLongitudinal(Integer tipoEstructuracionLongitudinal) {
        this.tipoEstructuracionLongitudinal = tipoEstructuracionLongitudinal;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
