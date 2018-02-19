package com.kgentreprises.hospital.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.kgentreprises.hospital.domain.enumeration.Regle;

/**
 * A Facture.
 */
@Entity
@Table(name = "facture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "montant")
    private Long montant;

    @Enumerated(EnumType.STRING)
    @Column(name = "regler")
    private Regle regler;

    @ManyToOne
    private Facturier facturier;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public Facture numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public Facture type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public Facture date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getMontant() {
        return montant;
    }

    public Facture montant(Long montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Regle getRegler() {
        return regler;
    }

    public Facture regler(Regle regler) {
        this.regler = regler;
        return this;
    }

    public void setRegler(Regle regler) {
        this.regler = regler;
    }

    public Facturier getFacturier() {
        return facturier;
    }

    public Facture facturier(Facturier facturier) {
        this.facturier = facturier;
        return this;
    }

    public void setFacturier(Facturier facturier) {
        this.facturier = facturier;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Facture facture = (Facture) o;
        if (facture.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), facture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Facture{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", type='" + getType() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", regler='" + getRegler() + "'" +
            "}";
    }
}
