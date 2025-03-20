package com.bridgecare.inventory.models.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PosicionGeograficaDTO {
    private BigDecimal latitud;
    private BigDecimal longitud;
    private BigDecimal altitud;
    private String coeficienteAceleracionSismica;
    private Boolean pasoCauce;
    private Boolean existeVariante;
    private BigDecimal longitudVariante;
    private String estado;

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
}
