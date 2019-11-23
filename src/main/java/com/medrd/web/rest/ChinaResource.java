package com.medrd.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.medrd.service.ChinaService;
import com.medrd.web.rest.errors.BadRequestAlertException;
import com.medrd.web.rest.util.HeaderUtil;
import com.medrd.web.rest.util.PaginationUtil;
import com.medrd.service.dto.ChinaDTO;
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
 * REST controller for managing China.
 */
@RestController
@RequestMapping("/api")
public class ChinaResource {

    private final Logger log = LoggerFactory.getLogger(ChinaResource.class);

    private static final String ENTITY_NAME = "china";

    private ChinaService chinaService;

    public ChinaResource(ChinaService chinaService) {
        this.chinaService = chinaService;
    }

    /**
     * POST  /chinas : Create a new china.
     *
     * @param chinaDTO the chinaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chinaDTO, or with status 400 (Bad Request) if the china has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chinas")
    @Timed
    public ResponseEntity<ChinaDTO> createChina(@Valid @RequestBody ChinaDTO chinaDTO) throws URISyntaxException {
        log.debug("REST request to save China : {}", chinaDTO);
        if (chinaDTO.getId() != null) {
            throw new BadRequestAlertException("A new china cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChinaDTO result = chinaService.save(chinaDTO);
        return ResponseEntity.created(new URI("/api/chinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chinas : Updates an existing china.
     *
     * @param chinaDTO the chinaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chinaDTO,
     * or with status 400 (Bad Request) if the chinaDTO is not valid,
     * or with status 500 (Internal Server Error) if the chinaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chinas")
    @Timed
    public ResponseEntity<ChinaDTO> updateChina(@Valid @RequestBody ChinaDTO chinaDTO) throws URISyntaxException {
        log.debug("REST request to update China : {}", chinaDTO);
        if (chinaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChinaDTO result = chinaService.save(chinaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chinaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chinas : get all the chinas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of chinas in body
     */
    @GetMapping("/chinas")
    @Timed
    public ResponseEntity<List<ChinaDTO>> getAllChinas(Pageable pageable) {
        log.debug("REST request to get a page of Chinas");
        Page<ChinaDTO> page = chinaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chinas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /chinas/:id : get the "id" china.
     *
     * @param id the id of the chinaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chinaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/chinas/{id}")
    @Timed
    public ResponseEntity<ChinaDTO> getChina(@PathVariable Long id) {
        log.debug("REST request to get China : {}", id);
        Optional<ChinaDTO> chinaDTO = chinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chinaDTO);
    }

    /**
     * DELETE  /chinas/:id : delete the "id" china.
     *
     * @param id the id of the chinaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chinas/{id}")
    @Timed
    public ResponseEntity<Void> deleteChina(@PathVariable Long id) {
        log.debug("REST request to delete China : {}", id);
        chinaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
