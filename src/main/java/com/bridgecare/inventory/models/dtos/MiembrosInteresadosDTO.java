package com.bridgecare.inventory.models.dtos;

import lombok.Data;

@Data
public class MiembrosInteresadosDTO {
    private String propietario;
    private String departamento;
    private String administradorVial;
    private String proyectista;
    private String municipio;

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
}
