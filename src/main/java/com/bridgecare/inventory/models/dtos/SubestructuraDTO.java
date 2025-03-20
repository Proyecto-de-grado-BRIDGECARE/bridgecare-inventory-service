package com.bridgecare.inventory.models.dtos;

import lombok.Data;

@Data
public class SubestructuraDTO {
    private EstriboDTO estribo;
    private DetalleDTO detalle;
    private SenialDTO senial;
    private PilaDTO pila;
}
