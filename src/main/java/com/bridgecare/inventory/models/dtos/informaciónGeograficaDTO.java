package com.bridgecare.inventory.models.dtos;

import lombok.Data;

@Data
public class informaciónGeograficaDTO {
    private Long inventarioId;
    private String nombrePuente;
    private String regional;
    private PosicionGeograficaDTO posicionGeografica;
}