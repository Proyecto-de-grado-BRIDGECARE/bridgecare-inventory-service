package com.bridgecare.inventory.models.dtos;

import lombok.Data;

@Data
public class SuperestructuraDTO {
    private String tipo;
    private Boolean disenioTipo;
    private Integer tipoEstructuracionTransversal;
    private Integer tipoEstructuracionLongitudinal;
    private Integer material;

    public String getTipo() {
        return tipo;
    }

    public Boolean getDisenioTipo() {
        return disenioTipo;
    }

    public Integer getTipoEstructuracionTransversal() {
        return tipoEstructuracionTransversal;
    }

    public Integer getTipoEstructuracionLongitudinal() {
        return tipoEstructuracionLongitudinal;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDisenioTipo(Boolean disenioTipo) {
        this.disenioTipo = disenioTipo;
    }

    public void setTipoEstructuracionTransversal(Integer tipoEstructuracionTransversal) {
        this.tipoEstructuracionTransversal = tipoEstructuracionTransversal;
    }

    public void setTipoEstructuracionLongitudinal(Integer tipoEstructuracionLongitudinal) {
        this.tipoEstructuracionLongitudinal = tipoEstructuracionLongitudinal;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }
}
