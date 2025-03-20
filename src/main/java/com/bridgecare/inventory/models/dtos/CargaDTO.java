package com.bridgecare.inventory.models.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CargaDTO {
    private BigDecimal longitudLuzCritica;
    private String factorClasificacion;
    private String fuerzaCortante;
    private String momento;
    private String lineaCargaPorRueda;

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
}
