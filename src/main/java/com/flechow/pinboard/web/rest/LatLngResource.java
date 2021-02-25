package com.flechow.pinboard.web.rest;

import com.flechow.pinboard.domain.LatLng;
import com.flechow.pinboard.service.LatLngService;
import com.flechow.pinboard.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.flechow.pinboard.domain.LatLng}.
 */
@RestController
@RequestMapping("/api")
public class LatLngResource {

    private final Logger log = LoggerFactory.getLogger(LatLngResource.class);

    private static final String ENTITY_NAME = "latLng";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LatLngService latLngService;

    public LatLngResource(LatLngService latLngService) {
        this.latLngService = latLngService;
    }

    /**
     * {@code POST  /lat-lngs} : Create a new latLng.
     *
     * @param latLng the latLng to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new latLng, or with status {@code 400 (Bad Request)} if the latLng has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lat-lngs")
    public ResponseEntity<LatLng> createLatLng(@RequestBody LatLng latLng) throws URISyntaxException {
        log.debug("REST request to save LatLng : {}", latLng);
        if (latLng.getId() != null) {
            throw new BadRequestAlertException("A new latLng cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LatLng result = latLngService.save(latLng);
        return ResponseEntity.created(new URI("/api/lat-lngs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lat-lngs} : Updates an existing latLng.
     *
     * @param latLng the latLng to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated latLng,
     * or with status {@code 400 (Bad Request)} if the latLng is not valid,
     * or with status {@code 500 (Internal Server Error)} if the latLng couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lat-lngs")
    public ResponseEntity<LatLng> updateLatLng(@RequestBody LatLng latLng) throws URISyntaxException {
        log.debug("REST request to update LatLng : {}", latLng);
        if (latLng.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LatLng result = latLngService.save(latLng);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, latLng.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lat-lngs} : get all the latLngs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of latLngs in body.
     */
    @GetMapping("/lat-lngs")
    public List<LatLng> getAllLatLngs() {
        log.debug("REST request to get all LatLngs");
        return latLngService.findAll();
    }

    /**
     * {@code GET  /lat-lngs/:id} : get the "id" latLng.
     *
     * @param id the id of the latLng to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the latLng, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lat-lngs/{id}")
    public ResponseEntity<LatLng> getLatLng(@PathVariable Long id) {
        log.debug("REST request to get LatLng : {}", id);
        Optional<LatLng> latLng = latLngService.findOne(id);
        return ResponseUtil.wrapOrNotFound(latLng);
    }

    /**
     * {@code DELETE  /lat-lngs/:id} : delete the "id" latLng.
     *
     * @param id the id of the latLng to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lat-lngs/{id}")
    public ResponseEntity<Void> deleteLatLng(@PathVariable Long id) {
        log.debug("REST request to delete LatLng : {}", id);
        latLngService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
