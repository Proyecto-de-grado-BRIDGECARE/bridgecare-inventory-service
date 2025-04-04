package com.bridgecare.inventory.models.dtos;

import java.util.List;

import com.bridgecare.common.models.dtos.PuenteDTO;
import com.bridgecare.common.models.dtos.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InventarioDTO {

    private String observaciones;

    private ApoyoDTO apoyo;

    private CargaDTO carga;

    @JsonProperty("datos_administrativos")
    private DatosAdministrativosDTO datosAdministrativos;

    @JsonProperty("datos_tecnicos")
    private DatosTecnicosDTO datosTecnicos;

    @JsonProperty("miembros_interesados")
    private MiembrosInteresadosDTO miembrosInteresados;

    private List<PasoDTO> pasos;

    @JsonProperty("posicion_geografica")
    private PosicionGeograficaDTO posicionGeografica;

    private SubestructuraDTO subestructura;

    private List<SuperestructuraDTO> superestructuras;

    private UsuarioDTO usuario;

    private PuenteDTO puente;

    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
