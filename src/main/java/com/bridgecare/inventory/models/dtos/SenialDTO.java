package com.bridgecare.inventory.models.dtos;

import lombok.Data;

@Data
public class SenialDTO {
    private String cargaMaxima;
    
    private String velocidadMaxima;

    private String otra;

    public String getCargaMaxima() {
        return cargaMaxima;
    }

    public String getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public String getOtra() {
        return otra;
    }

    public void setCargaMaxima(String cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    public void setVelocidadMaxima(String velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public void setOtra(String otra) {
        this.otra = otra;
    }
}
