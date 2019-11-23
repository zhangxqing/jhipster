package com.medrd.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.medrd.service.HospitalService;
import com.medrd.web.rest.errors.BadRequestAlertException;
import com.medrd.web.rest.util.HeaderUtil;
import com.medrd.web.rest.util.PaginationUtil;
import com.medrd.service.dto.HospitalDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Hospital.
 */
@RestController
@RequestMapping("/api")
public class HospitalResource {

    private final Logger log = LoggerFactory.getLogger(HospitalResource.class);

    private static final String ENTITY_NAME = "hospital";

    private HospitalService hospitalService;

    public HospitalResource(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    /**
     * POST  /hospitals : Create a new hospital.
     *
     * @param hospitalDTO the hospitalDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hospitalDTO, or with status 400 (Bad Request) if the hospital has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hospitals")
    @Timed
    public ResponseEntity<HospitalDTO> createHospital(@Valid @RequestBody HospitalDTO hospitalDTO) throws URISyntaxException {
        log.debug("REST request to save Hospital : {}", hospitalDTO);
        if (hospitalDTO.getId() != null) {
            throw new BadRequestAlertException("A new hospital cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HospitalDTO result = hospitalService.save(hospitalDTO);
        return ResponseEntity.created(new URI("/api/hospitals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hospitals : Updates an existing hospital.
     *
     * @param hospitalDTO the hospitalDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hospitalDTO,
     * or with status 400 (Bad Request) if the hospitalDTO is not valid,
     * or with status 500 (Internal Server Error) if the hospitalDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hospitals")
    @Timed
    public ResponseEntity<HospitalDTO> updateHospital(@Valid @RequestBody HospitalDTO hospitalDTO) throws URISyntaxException {
        log.debug("REST request to update Hospital : {}", hospitalDTO);
        if (hospitalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HospitalDTO result = hospitalService.save(hospitalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hospitalDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hospitals : get all the hospitals.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of hospitals in body
     */
    @GetMapping("/hospitals")
    @Timed
    public ResponseEntity<List<HospitalDTO>> getAllHospitals(Pageable pageable) {
        log.debug("REST request to get a page of Hospitals");
        Page<HospitalDTO> page = hospitalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hospitals");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /hospitals/:id : get the "id" hospital.
     *
     * @param id the id of the hospitalDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hospitalDTO, or with status 404 (Not Found)
     */
    @GetMapping("/hospitals/{id}")
    @Timed
    public ResponseEntity<HospitalDTO> getHospital(@PathVariable Long id) {
        log.debug("REST request to get Hospital : {}", id);
        Optional<HospitalDTO> hospitalDTO = hospitalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hospitalDTO);
    }

    /**
     * DELETE  /hospitals/:id : delete the "id" hospital.
     *
     * @param id the id of the hospitalDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hospitals/{id}")
    @Timed
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
        log.debug("REST request to delete Hospital : {}", id);
        hospitalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
