package com.kgentreprises.hospital.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.kgentreprises.hospital.domain.enumeration.Regle;

/**
 * A DTO for the Facture entity.
 */
public class FactureDTO implements Serializable {

    private Long id;

    private String numero;

    private String type;

    private LocalDate date;

    private Long montant;

    private Regle regler;

    private Long facturierId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Regle getRegler() {
        return regler;
    }

    public void setRegler(Regle regler) {
        this.regler = regler;
    }

    public Long getFacturierId() {
        return facturierId;
    }

    public void setFacturierId(Long facturierId) {
        this.facturierId = facturierId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FactureDTO factureDTO = (FactureDTO) o;
        if(factureDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), factureDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FactureDTO{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", type='" + getType() + "'" +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", regler='" + getRegler() + "'" +
            "}";
    }
}
