package com.bridgecare.inventory.models.dtos;

import java.util.List;

import com.bridgecare.common.models.dtos.PuenteDTO;
import com.bridgecare.common.models.dtos.UsuarioDTO;

import lombok.Data;

@Data
public class InventarioDTO {
    private String observaciones;
    private ApoyoDTO apoyo;
    private CargaDTO carga;
    private DatosAdministrativosDTO datosAdministrativos;
    private DatosTecnicosDTO datosTecnicos;
    private MiembrosInteresadosDTO miembrosInteresados;
    private List<PasoDTO> pasos;
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
