package com.bridgecare.inventory.models.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "datos_administrativos")
public class DatosAdministrativos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "anio_construccion")
    private Integer anioConstruccion;

    @Column(name = "anio_reconstruccion")
    private Integer anioReconstruccion;

    @Column(name = "direccion_absc_carretera")
    private String direccionAbscCarretera;

    @Column(name = "requisitos_inspeccion")
    private String requisitosInspeccion;

    @Column(name = "numero_secciones_inspeccion")
    private String numeroSeccionesInspeccion;

    @Column(name = "estacion_conteo")
    private String estacionConteo;

    @Column(name = "fecha_recoleccion_datos")
    private LocalDate fechaRecoleccionDatos;

    @OneToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

    public Integer getAnioConstruccion() {
        return anioConstruccion;
    }

    public Integer getAnioReconstruccion() {
        return anioReconstruccion;
    }

    public String getDireccionAbscCarretera() {
        return direccionAbscCarretera;
    }

    public String getRequisitosInspeccion() {
        return requisitosInspeccion;
    }

    public String getNumeroSeccionesInspeccion() {
        return numeroSeccionesInspeccion;
    }

    public String getEstacionConteo() {
        return estacionConteo;
    }

    public LocalDate getFechaRecoleccionDatos() {
        return fechaRecoleccionDatos;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnioConstruccion(Integer anioConstruccion) {
        this.anioConstruccion = anioConstruccion;
    }

    public void setAnioReconstruccion(Integer anioReconstruccion) {
        this.anioReconstruccion = anioReconstruccion;
    }

    public void setDireccionAbscCarretera(String direccionAbscCarretera) {
        this.direccionAbscCarretera = direccionAbscCarretera;
    }

    public void setRequisitosInspeccion(String requisitosInspeccion) {
        this.requisitosInspeccion = requisitosInspeccion;
    }

    public void setNumeroSeccionesInspeccion(String numeroSeccionesInspeccion) {
        this.numeroSeccionesInspeccion = numeroSeccionesInspeccion;
    }

    public void setEstacionConteo(String estacionConteo) {
        this.estacionConteo = estacionConteo;
    }

    public void setFechaRecoleccionDatos(LocalDate fechaRecoleccionDatos) {
        this.fechaRecoleccionDatos = fechaRecoleccionDatos;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
