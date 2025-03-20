package com.bridgecare.inventory.models.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DatosTecnicosDTO {
    private Integer numeroLuces;
    private BigDecimal longitudLuzMenor;
    private BigDecimal longitudLuzMayor;
    private BigDecimal longitudTotal;
    private BigDecimal anchoTablero;
    private BigDecimal anchoSeparador;
    private BigDecimal anchoAndenIzq;
    private BigDecimal anchoAndenDer;
    private BigDecimal anchoCalzada;
    private BigDecimal anchoEntreBordillos;
    private BigDecimal anchoAcceso;
    private BigDecimal alturaPilas;
    private BigDecimal alturaEstribos;
    private BigDecimal longitudApoyoPilas;
    private BigDecimal longitudApoyoEstribos;
    private Boolean puenteTerraplen;
    private String puenteCurvaTangente;
    private BigDecimal esviajamiento;

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
}
