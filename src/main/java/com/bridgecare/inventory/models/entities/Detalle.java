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
@Table(name = "detalle")
public class Detalle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_baranda")
    private Integer tipoBaranda;
    
    @Column(name = "superficie_rodadura")
    private Integer superficieRodadura;

    @Column(name = "junta_expansion")
    private Integer juntaExpansion;

    @OneToOne
    @JoinColumn(name = "subestructura_id", nullable = false)
    private Subestructura subestructura;

    public Long getId() {
        return id;
    }

    public Integer getTipoBaranda() {
        return tipoBaranda;
    }

    public Integer getSuperficieRodadura() {
        return superficieRodadura;
    }

    public Integer getJuntaExpansion() {
        return juntaExpansion;
    }

    public Subestructura getSubestructura() {
        return subestructura;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setSubestructura(Subestructura subestructura) {
        this.subestructura = subestructura;
    }
}
