package com.kgentreprises.hospital.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.kgentreprises.hospital.domain.Facturier;

import com.kgentreprises.hospital.repository.FacturierRepository;
import com.kgentreprises.hospital.web.rest.errors.BadRequestAlertException;
import com.kgentreprises.hospital.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Facturier.
 */
@RestController
@RequestMapping("/api")
public class FacturierResource {

    private final Logger log = LoggerFactory.getLogger(FacturierResource.class);

    private static final String ENTITY_NAME = "facturier";

    private final FacturierRepository facturierRepository;

    public FacturierResource(FacturierRepository facturierRepository) {
        this.facturierRepository = facturierRepository;
    }

    /**
     * POST  /facturiers : Create a new facturier.
     *
     * @param facturier the facturier to create
     * @return the ResponseEntity with status 201 (Created) and with body the new facturier, or with status 400 (Bad Request) if the facturier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/facturiers")
    @Timed
    public ResponseEntity<Facturier> createFacturier(@RequestBody Facturier facturier) throws URISyntaxException {
        log.debug("REST request to save Facturier : {}", facturier);
        if (facturier.getId() != null) {
            throw new BadRequestAlertException("A new facturier cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Facturier result = facturierRepository.save(facturier);
        return ResponseEntity.created(new URI("/api/facturiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /facturiers : Updates an existing facturier.
     *
     * @param facturier the facturier to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated facturier,
     * or with status 400 (Bad Request) if the facturier is not valid,
     * or with status 500 (Internal Server Error) if the facturier couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/facturiers")
    @Timed
    public ResponseEntity<Facturier> updateFacturier(@RequestBody Facturier facturier) throws URISyntaxException {
        log.debug("REST request to update Facturier : {}", facturier);
        if (facturier.getId() == null) {
            return createFacturier(facturier);
        }
        Facturier result = facturierRepository.save(facturier);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, facturier.getId().toString()))
            .body(result);
    }

    /**
     * GET  /facturiers : get all the facturiers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of facturiers in body
     */
    @GetMapping("/facturiers")
    @Timed
    public List<Facturier> getAllFacturiers() {
        log.debug("REST request to get all Facturiers");
        return facturierRepository.findAll();
        }

    /**
     * GET  /facturiers/:id : get the "id" facturier.
     *
     * @param id the id of the facturier to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the facturier, or with status 404 (Not Found)
     */
    @GetMapping("/facturiers/{id}")
    @Timed
    public ResponseEntity<Facturier> getFacturier(@PathVariable Long id) {
        log.debug("REST request to get Facturier : {}", id);
        Facturier facturier = facturierRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(facturier));
    }

    /**
     * DELETE  /facturiers/:id : delete the "id" facturier.
     *
     * @param id the id of the facturier to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/facturiers/{id}")
    @Timed
    public ResponseEntity<Void> deleteFacturier(@PathVariable Long id) {
        log.debug("REST request to delete Facturier : {}", id);
        facturierRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
