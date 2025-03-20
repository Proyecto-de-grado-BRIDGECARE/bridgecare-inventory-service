package com.bridgecare.inventory.models.dtos;

import lombok.Data;

@Data
public class EstriboDTO {
    private Integer tipo;
    private Integer material;
    private Integer tipoCimentacion;

    public Integer getTipo() {
        return tipo;
    }

    public Integer getMaterial() {
        return material;
    }

    public Integer getTipoCimentacion() {
        return tipoCimentacion;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public void setTipoCimentacion(Integer tipoCimentacion) {
        this.tipoCimentacion = tipoCimentacion;
    }
}
