package com.medrd.web.rest;

import com.medrd.JhipsterApp;

import com.medrd.domain.China;
import com.medrd.repository.ChinaRepository;
import com.medrd.service.ChinaService;
import com.medrd.service.dto.ChinaDTO;
import com.medrd.service.mapper.ChinaMapper;
import com.medrd.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.medrd.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ChinaResource REST controller.
 *
 * @see ChinaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class ChinaResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_P_ID = 1L;
    private static final Long UPDATED_P_ID = 2L;

    @Autowired
    private ChinaRepository chinaRepository;

    @Autowired
    private ChinaMapper chinaMapper;
    
    @Autowired
    private ChinaService chinaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChinaMockMvc;

    private China china;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChinaResource chinaResource = new ChinaResource(chinaService);
        this.restChinaMockMvc = MockMvcBuilders.standaloneSetup(chinaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static China createEntity(EntityManager em) {
        China china = new China()
            .name(DEFAULT_NAME)
            .pId(DEFAULT_P_ID);
        return china;
    }

    @Before
    public void initTest() {
        china = createEntity(em);
    }

    @Test
    @Transactional
    public void createChina() throws Exception {
        int databaseSizeBeforeCreate = chinaRepository.findAll().size();

        // Create the China
        ChinaDTO chinaDTO = chinaMapper.toDto(china);
        restChinaMockMvc.perform(post("/api/chinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chinaDTO)))
            .andExpect(status().isCreated());

        // Validate the China in the database
        List<China> chinaList = chinaRepository.findAll();
        assertThat(chinaList).hasSize(databaseSizeBeforeCreate + 1);
        China testChina = chinaList.get(chinaList.size() - 1);
        assertThat(testChina.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testChina.getpId()).isEqualTo(DEFAULT_P_ID);
    }

    @Test
    @Transactional
    public void createChinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chinaRepository.findAll().size();

        // Create the China with an existing ID
        china.setId(1L);
        ChinaDTO chinaDTO = chinaMapper.toDto(china);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChinaMockMvc.perform(post("/api/chinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the China in the database
        List<China> chinaList = chinaRepository.findAll();
        assertThat(chinaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllChinas() throws Exception {
        // Initialize the database
        chinaRepository.saveAndFlush(china);

        // Get all the chinaList
        restChinaMockMvc.perform(get("/api/chinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(china.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].pId").value(hasItem(DEFAULT_P_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getChina() throws Exception {
        // Initialize the database
        chinaRepository.saveAndFlush(china);

        // Get the china
        restChinaMockMvc.perform(get("/api/chinas/{id}", china.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(china.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.pId").value(DEFAULT_P_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingChina() throws Exception {
        // Get the china
        restChinaMockMvc.perform(get("/api/chinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChina() throws Exception {
        // Initialize the database
        chinaRepository.saveAndFlush(china);

        int databaseSizeBeforeUpdate = chinaRepository.findAll().size();

        // Update the china
        China updatedChina = chinaRepository.findById(china.getId()).get();
        // Disconnect from session so that the updates on updatedChina are not directly saved in db
        em.detach(updatedChina);
        updatedChina
            .name(UPDATED_NAME)
            .pId(UPDATED_P_ID);
        ChinaDTO chinaDTO = chinaMapper.toDto(updatedChina);

        restChinaMockMvc.perform(put("/api/chinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chinaDTO)))
            .andExpect(status().isOk());

        // Validate the China in the database
        List<China> chinaList = chinaRepository.findAll();
        assertThat(chinaList).hasSize(databaseSizeBeforeUpdate);
        China testChina = chinaList.get(chinaList.size() - 1);
        assertThat(testChina.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testChina.getpId()).isEqualTo(UPDATED_P_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingChina() throws Exception {
        int databaseSizeBeforeUpdate = chinaRepository.findAll().size();

        // Create the China
        ChinaDTO chinaDTO = chinaMapper.toDto(china);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChinaMockMvc.perform(put("/api/chinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the China in the database
        List<China> chinaList = chinaRepository.findAll();
        assertThat(chinaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChina() throws Exception {
        // Initialize the database
        chinaRepository.saveAndFlush(china);

        int databaseSizeBeforeDelete = chinaRepository.findAll().size();

        // Get the china
        restChinaMockMvc.perform(delete("/api/chinas/{id}", china.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<China> chinaList = chinaRepository.findAll();
        assertThat(chinaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(China.class);
        China china1 = new China();
        china1.setId(1L);
        China china2 = new China();
        china2.setId(china1.getId());
        assertThat(china1).isEqualTo(china2);
        china2.setId(2L);
        assertThat(china1).isNotEqualTo(china2);
        china1.setId(null);
        assertThat(china1).isNotEqualTo(china2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChinaDTO.class);
        ChinaDTO chinaDTO1 = new ChinaDTO();
        chinaDTO1.setId(1L);
        ChinaDTO chinaDTO2 = new ChinaDTO();
        assertThat(chinaDTO1).isNotEqualTo(chinaDTO2);
        chinaDTO2.setId(chinaDTO1.getId());
        assertThat(chinaDTO1).isEqualTo(chinaDTO2);
        chinaDTO2.setId(2L);
        assertThat(chinaDTO1).isNotEqualTo(chinaDTO2);
        chinaDTO1.setId(null);
        assertThat(chinaDTO1).isNotEqualTo(chinaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(chinaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(chinaMapper.fromId(null)).isNull();
    }
}
