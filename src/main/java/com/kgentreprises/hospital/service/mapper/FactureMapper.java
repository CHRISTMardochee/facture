package com.kgentreprises.hospital.service.mapper;

import com.kgentreprises.hospital.domain.*;
import com.kgentreprises.hospital.service.dto.FactureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Facture and its DTO FactureDTO.
 */
@Mapper(componentModel = "spring", uses = {FacturierMapper.class})
public interface FactureMapper extends EntityMapper<FactureDTO, Facture> {

    @Mapping(source = "facturier.id", target = "facturierId")
    FactureDTO toDto(Facture facture);

    @Mapping(source = "facturierId", target = "facturier")
    Facture toEntity(FactureDTO factureDTO);

    default Facture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Facture facture = new Facture();
        facture.setId(id);
        return facture;
    }
}
