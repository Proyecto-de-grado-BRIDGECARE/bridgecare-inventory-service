package com.bridgecare.inventory.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "apoyo")
public class Apoyo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fijo_sobre_estribo")
    private Integer fijoSobreEstribo;

    @Column(name = "movil_sobre_estribo")
    private Integer movilSobreEstribo;

    @Column(name = "fijo_en_pila")
    private Integer fijoEnPila;

    @Column(name = "movil_en_pila")
    private Integer movilEnPila;

    @Column(name = "fijo_en_viga")
    private Integer fijoEnViga;

    @Column(name = "movil_en_viga")
    private Integer movilEnViga;

    @Column(name = "vehiculo_disenio")
    private Integer vehiculoDisenio;

    @Column(name = "clase_distribucion_carga")
    private Integer claseDistribucionCarga;

    @OneToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

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

    public Inventario getInventario() {
        return inventario;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
