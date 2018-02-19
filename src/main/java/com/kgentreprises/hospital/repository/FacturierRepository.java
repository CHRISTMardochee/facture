package com.kgentreprises.hospital.repository;

import com.kgentreprises.hospital.domain.Facturier;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Facturier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacturierRepository extends JpaRepository<Facturier, Long> {

}
