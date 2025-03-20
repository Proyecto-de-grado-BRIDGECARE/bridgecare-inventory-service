package com.bridgecare.inventory.models.dtos;

import lombok.Data;

@Data
public class ApoyoDTO {
    private Integer fijoSobreEstribo;
    private Integer movilSobreEstribo;
    private Integer fijoEnPila;
    private Integer movilEnPila;
    private Integer fijoEnViga;
    private Integer movilEnViga;
    private Integer vehiculoDisenio;
    private Integer claseDistribucionCarga;

    public Integer getFijoSobreEstribo() {
        return fijoSobreEstribo;
    }

    public Integer getMovilSobreEstribo() {
        return movilSobreEstribo;
    }

    public Integer getFijoEnPila() {
        return fijoEnPila;
    }

    public Integer getMovilEnPila() {
        return movilEnPila;
    }

    public Integer getFijoEnViga() {
        return fijoEnViga;
    }

    public Integer getMovilEnViga() {
        return movilEnViga;
    }

    public Integer getVehiculoDisenio() {
        return vehiculoDisenio;
    }

    public Integer getClaseDistribucionCarga() {
        return claseDistribucionCarga;
    }

    public void setFijoSobreEstribo(Integer fijoSobreEstribo) {
        this.fijoSobreEstribo = fijoSobreEstribo;
    }

    public void setMovilSobreEstribo(Integer movilSobreEstribo) {
        this.movilSobreEstribo = movilSobreEstribo;
    }

    public void setFijoEnPila(Integer fijoEnPila) {
        this.fijoEnPila = fijoEnPila;
    }

    public void setMovilEnPila(Integer movilEnPila) {
        this.movilEnPila = movilEnPila;
    }

    public void setFijoEnViga(Integer fijoEnViga) {
        this.fijoEnViga = fijoEnViga;
    }

    public void setMovilEnViga(Integer movilEnViga) {
        this.movilEnViga = movilEnViga;
    }

    public void setVehiculoDisenio(Integer vehiculoDisenio) {
        this.vehiculoDisenio = vehiculoDisenio;
    }

    public void setClaseDistribucionCarga(Integer claseDistribucionCarga) {
        this.claseDistribucionCarga = claseDistribucionCarga;
    }
}
