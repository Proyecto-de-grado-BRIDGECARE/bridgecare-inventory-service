package com.bridgecare.inventory.models.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PasoDTO {
    private Integer numero;
    private String tipoPaso;
    private Boolean primero;
    private String supInf;
    private BigDecimal galiboI;
    private BigDecimal galiboIm;
    private BigDecimal galiboDm;
    private BigDecimal galiboD;

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
}
