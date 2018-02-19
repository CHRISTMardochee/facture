package com.kgentreprises.hospital.service.mapper;

import com.kgentreprises.hospital.domain.*;
import com.kgentreprises.hospital.service.dto.FacturierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Facturier and its DTO FacturierDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FacturierMapper extends EntityMapper<FacturierDTO, Facturier> {


    @Mapping(target = "names", ignore = true)
    Facturier toEntity(FacturierDTO facturierDTO);

    default Facturier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Facturier facturier = new Facturier();
        facturier.setId(id);
        return facturier;
    }
}
