package com.bridgecare.inventory.models.dtos;

import lombok.Data;

@Data
public class DetalleDTO {
    private Integer tipoBaranda;
    private Integer superficieRodadura;
    private Integer juntaExpansion;

    public Integer getTipoBaranda() {
        return tipoBaranda;
    }

    public Integer getSuperficieRodadura() {
        return superficieRodadura;
    }

    public Integer getJuntaExpansion() {
        return juntaExpansion;
    }

    public void setTipoBaranda(Integer tipoBaranda) {
        this.tipoBaranda = tipoBaranda;
    }

    public void setSuperficieRodadura(Integer superficieRodadura) {
        this.superficieRodadura = superficieRodadura;
    }

    public void setJuntaExpansion(Integer juntaExpansion) {
        this.juntaExpansion = juntaExpansion;
    }
}
