package com.bridgecare.inventory.models.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DatosAdministrativosDTO {
    private Integer anioConstruccion;
    private Integer anioReconstruccion;
    private String direccionAbscCarretera;
    private String requisitosInspeccion;
    private String numeroSeccionesInspeccion;
    private String estacionConteo;
    private LocalDate fechaRecoleccionDatos;

    public Integer getAnioConstruccion() {
        return anioConstruccion;
    }

    public Integer getAnioReconstruccion() {
        return anioReconstruccion;
    }

    public String getDireccionAbscCarretera() {
        return direccionAbscCarretera;
    }

    public String getRequisitosInspeccion() {
        return requisitosInspeccion;
    }

    public String getNumeroSeccionesInspeccion() {
        return numeroSeccionesInspeccion;
    }

    public String getEstacionConteo() {
        return estacionConteo;
    }

    public LocalDate getFechaRecoleccionDatos() {
        return fechaRecoleccionDatos;
    }

    public void setAnioConstruccion(Integer anioConstruccion) {
        this.anioConstruccion = anioConstruccion;
    }

    public void setAnioReconstruccion(Integer anioReconstruccion) {
        this.anioReconstruccion = anioReconstruccion;
    }

    public void setDireccionAbscCarretera(String direccionAbscCarretera) {
        this.direccionAbscCarretera = direccionAbscCarretera;
    }

    public void setRequisitosInspeccion(String requisitosInspeccion) {
        this.requisitosInspeccion = requisitosInspeccion;
    }

    public void setNumeroSeccionesInspeccion(String numeroSeccionesInspeccion) {
        this.numeroSeccionesInspeccion = numeroSeccionesInspeccion;
    }

    public void setEstacionConteo(String estacionConteo) {
        this.estacionConteo = estacionConteo;
    }

    public void setFechaRecoleccionDatos(LocalDate fechaRecoleccionDatos) {
        this.fechaRecoleccionDatos = fechaRecoleccionDatos;
    }
}
