package com.bridgecare.inventory.models.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "datos_tecnicos")
public class DatosTecnicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_luces")
    private Integer numeroLuces;

    @Column(name = "longitud_luz_menor")
    private BigDecimal longitudLuzMenor;

    @Column(name = "longitud_luz_mayor")
    private BigDecimal longitudLuzMayor;

    @Column(name = "longitud_total")
    private BigDecimal longitudTotal;

    @Column(name = "ancho_tablero")
    private BigDecimal anchoTablero;

    @Column(name = "ancho_separador")
    private BigDecimal anchoSeparador;

    @Column(name = "ancho_anden_izq")
    private BigDecimal anchoAndenIzq;

    @Column(name = "ancho_anden_der")
    private BigDecimal anchoAndenDer;

    @Column(name = "ancho_calzada")
    private BigDecimal anchoCalzada;

    @Column(name = "ancho_entre_bordillos")
    private BigDecimal anchoEntreBordillos;

    @Column(name = "ancho_acceso")
    private BigDecimal anchoAcceso;

    @Column(name = "altura_pilas")
    private BigDecimal alturaPilas;

    @Column(name = "altura_estribos")
    private BigDecimal alturaEstribos;

    @Column(name = "longitud_apoyo_pilas")
    private BigDecimal longitudApoyoPilas;

    @Column(name = "longitud_apoyo_estribos")
    private BigDecimal longitudApoyoEstribos;

    @Column(name = "puente_terraplen")
    private Boolean puenteTerraplen;

    @Column(name = "puente_curva_tangente")
    private String puenteCurvaTangente;

    @Column(name = "esviajamiento")
    private BigDecimal esviajamiento;
    
    @OneToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    private Inventario inventario;

    public Long getId() {
        return id;
    }

    public Integer getNumeroLuces() {
        return numeroLuces;
    }

    public BigDecimal getLongitudLuzMenor() {
        return longitudLuzMenor;
    }

    public BigDecimal getLongitudLuzMayor() {
        return longitudLuzMayor;
    }

    public BigDecimal getLongitudTotal() {
        return longitudTotal;
    }

    public BigDecimal getAnchoTablero() {
        return anchoTablero;
    }

    public BigDecimal getAnchoSeparador() {
        return anchoSeparador;
    }

    public BigDecimal getAnchoAndenIzq() {
        return anchoAndenIzq;
    }

    public BigDecimal getAnchoAndenDer() {
        return anchoAndenDer;
    }

    public BigDecimal getAnchoCalzada() {
        return anchoCalzada;
    }

    public BigDecimal getAnchoEntreBordillos() {
        return anchoEntreBordillos;
    }

    public BigDecimal getAnchoAcceso() {
        return anchoAcceso;
    }

    public BigDecimal getAlturaPilas() {
        return alturaPilas;
    }

    public BigDecimal getAlturaEstribos() {
        return alturaEstribos;
    }

    public BigDecimal getLongitudApoyoPilas() {
        return longitudApoyoPilas;
    }

    public BigDecimal getLongitudApoyoEstribos() {
        return longitudApoyoEstribos;
    }

    public Boolean getPuenteTerraplen() {
        return puenteTerraplen;
    }

    public String getPuenteCurvaTangente() {
        return puenteCurvaTangente;
    }

    public BigDecimal getEsviajamiento() {
        return esviajamiento;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroLuces(Integer numeroLuces) {
        this.numeroLuces = numeroLuces;
    }

    public void setLongitudLuzMenor(BigDecimal longitudLuzMenor) {
        this.longitudLuzMenor = longitudLuzMenor;
    }

    public void setLongitudLuzMayor(BigDecimal longitudLuzMayor) {
        this.longitudLuzMayor = longitudLuzMayor;
    }

    public void setLongitudTotal(BigDecimal longitudTotal) {
        this.longitudTotal = longitudTotal;
    }

    public void setAnchoTablero(BigDecimal anchoTablero) {
        this.anchoTablero = anchoTablero;
    }

    public void setAnchoSeparador(BigDecimal anchoSeparador) {
        this.anchoSeparador = anchoSeparador;
    }

    public void setAnchoAndenIzq(BigDecimal anchoAndenIzq) {
        this.anchoAndenIzq = anchoAndenIzq;
    }

    public void setAnchoAndenDer(BigDecimal anchoAndenDer) {
        this.anchoAndenDer = anchoAndenDer;
    }

    public void setAnchoCalzada(BigDecimal anchoCalzada) {
        this.anchoCalzada = anchoCalzada;
    }

    public void setAnchoEntreBordillos(BigDecimal anchoEntreBordillos) {
        this.anchoEntreBordillos = anchoEntreBordillos;
    }

    public void setAnchoAcceso(BigDecimal anchoAcceso) {
        this.anchoAcceso = anchoAcceso;
    }

    public void setAlturaPilas(BigDecimal alturaPilas) {
        this.alturaPilas = alturaPilas;
    }

    public void setAlturaEstribos(BigDecimal alturaEstribos) {
        this.alturaEstribos = alturaEstribos;
    }

    public void setLongitudApoyoPilas(BigDecimal longitudApoyoPilas) {
        this.longitudApoyoPilas = longitudApoyoPilas;
    }

    public void setLongitudApoyoEstribos(BigDecimal longitudApoyoEstribos) {
        this.longitudApoyoEstribos = longitudApoyoEstribos;
    }

    public void setPuenteTerraplen(Boolean puenteTerraplen) {
        this.puenteTerraplen = puenteTerraplen;
    }

    public void setPuenteCurvaTangente(String puenteCurvaTangente) {
        this.puenteCurvaTangente = puenteCurvaTangente;
    }

    public void setEsviajamiento(BigDecimal esviajamiento) {
        this.esviajamiento = esviajamiento;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
}
