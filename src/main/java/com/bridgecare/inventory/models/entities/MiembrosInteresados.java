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
@Table(name = "miembros_interesados")
public class MiembrosInteresados {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "propietario")
    private String propietario;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "administrador_vial")
    private String administradorVial;

    @Column(name = "proyectista")
    private String proyectista;

    @Column(name = "municipio")
    private String municipio;

    @OneToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getAdministradorVial() {
        return administradorVial;
    }

    public String getProyectista() {
        return proyectista;
    }

    public String getMunicipio() {
        return municipio;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setAdministradorVial(String administradorVial) {
        this.administradorVial = administradorVial;
    }

    public void setProyectista(String proyectista) {
        this.proyectista = proyectista;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
