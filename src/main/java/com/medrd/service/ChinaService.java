package com.medrd.service;

import com.medrd.domain.China;
import com.medrd.repository.ChinaRepository;
import com.medrd.service.dto.ChinaDTO;
import com.medrd.service.mapper.ChinaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing China.
 */
@Service
@Transactional
public class ChinaService {

    private final Logger log = LoggerFactory.getLogger(ChinaService.class);

    private ChinaRepository chinaRepository;

    private ChinaMapper chinaMapper;

    public ChinaService(ChinaRepository chinaRepository, ChinaMapper chinaMapper) {
        this.chinaRepository = chinaRepository;
        this.chinaMapper = chinaMapper;
    }

    /**
     * Save a china.
     *
     * @param chinaDTO the entity to save
     * @return the persisted entity
     */
    public ChinaDTO save(ChinaDTO chinaDTO) {
        log.debug("Request to save China : {}", chinaDTO);

        China china = chinaMapper.toEntity(chinaDTO);
        china = chinaRepository.save(china);
        return chinaMapper.toDto(china);
    }

    /**
     * Get all the chinas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<ChinaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Chinas");
        return chinaRepository.findAll(pageable)
            .map(chinaMapper::toDto);
    }


    /**
     * Get one china by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ChinaDTO> findOne(Long id) {
        log.debug("Request to get China : {}", id);
        return chinaRepository.findById(id)
            .map(chinaMapper::toDto);
    }

    /**
     * Delete the china by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete China : {}", id);
        chinaRepository.deleteById(id);
    }
}
