package com.kgentreprises.hospital.web.rest;

import com.kgentreprises.hospital.HospitalApp;

import com.kgentreprises.hospital.domain.Facturier;
import com.kgentreprises.hospital.repository.FacturierRepository;
import com.kgentreprises.hospital.web.rest.errors.ExceptionTranslator;

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

import static com.kgentreprises.hospital.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FacturierResource REST controller.
 *
 * @see FacturierResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HospitalApp.class)
public class FacturierResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTNAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_STREET_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_STREET_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_STATE_PROVINCE = "BBBBBBBBBB";

    @Autowired
    private FacturierRepository facturierRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFacturierMockMvc;

    private Facturier facturier;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FacturierResource facturierResource = new FacturierResource(facturierRepository);
        this.restFacturierMockMvc = MockMvcBuilders.standaloneSetup(facturierResource)
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
    public static Facturier createEntity(EntityManager em) {
        Facturier facturier = new Facturier()
            .name(DEFAULT_NAME)
            .firstname(DEFAULT_FIRSTNAME)
            .streetAddress(DEFAULT_STREET_ADDRESS)
            .postalCode(DEFAULT_POSTAL_CODE)
            .city(DEFAULT_CITY)
            .stateProvince(DEFAULT_STATE_PROVINCE);
        return facturier;
    }

    @Before
    public void initTest() {
        facturier = createEntity(em);
    }

    @Test
    @Transactional
    public void createFacturier() throws Exception {
        int databaseSizeBeforeCreate = facturierRepository.findAll().size();

        // Create the Facturier
        restFacturierMockMvc.perform(post("/api/facturiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facturier)))
            .andExpect(status().isCreated());

        // Validate the Facturier in the database
        List<Facturier> facturierList = facturierRepository.findAll();
        assertThat(facturierList).hasSize(databaseSizeBeforeCreate + 1);
        Facturier testFacturier = facturierList.get(facturierList.size() - 1);
        assertThat(testFacturier.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFacturier.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testFacturier.getStreetAddress()).isEqualTo(DEFAULT_STREET_ADDRESS);
        assertThat(testFacturier.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testFacturier.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testFacturier.getStateProvince()).isEqualTo(DEFAULT_STATE_PROVINCE);
    }

    @Test
    @Transactional
    public void createFacturierWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = facturierRepository.findAll().size();

        // Create the Facturier with an existing ID
        facturier.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFacturierMockMvc.perform(post("/api/facturiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facturier)))
            .andExpect(status().isBadRequest());

        // Validate the Facturier in the database
        List<Facturier> facturierList = facturierRepository.findAll();
        assertThat(facturierList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFacturiers() throws Exception {
        // Initialize the database
        facturierRepository.saveAndFlush(facturier);

        // Get all the facturierList
        restFacturierMockMvc.perform(get("/api/facturiers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facturier.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME.toString())))
            .andExpect(jsonPath("$.[*].streetAddress").value(hasItem(DEFAULT_STREET_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].stateProvince").value(hasItem(DEFAULT_STATE_PROVINCE.toString())));
    }

    @Test
    @Transactional
    public void getFacturier() throws Exception {
        // Initialize the database
        facturierRepository.saveAndFlush(facturier);

        // Get the facturier
        restFacturierMockMvc.perform(get("/api/facturiers/{id}", facturier.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(facturier.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME.toString()))
            .andExpect(jsonPath("$.streetAddress").value(DEFAULT_STREET_ADDRESS.toString()))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.stateProvince").value(DEFAULT_STATE_PROVINCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFacturier() throws Exception {
        // Get the facturier
        restFacturierMockMvc.perform(get("/api/facturiers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFacturier() throws Exception {
        // Initialize the database
        facturierRepository.saveAndFlush(facturier);
        int databaseSizeBeforeUpdate = facturierRepository.findAll().size();

        // Update the facturier
        Facturier updatedFacturier = facturierRepository.findOne(facturier.getId());
        // Disconnect from session so that the updates on updatedFacturier are not directly saved in db
        em.detach(updatedFacturier);
        updatedFacturier
            .name(UPDATED_NAME)
            .firstname(UPDATED_FIRSTNAME)
            .streetAddress(UPDATED_STREET_ADDRESS)
            .postalCode(UPDATED_POSTAL_CODE)
            .city(UPDATED_CITY)
            .stateProvince(UPDATED_STATE_PROVINCE);

        restFacturierMockMvc.perform(put("/api/facturiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFacturier)))
            .andExpect(status().isOk());

        // Validate the Facturier in the database
        List<Facturier> facturierList = facturierRepository.findAll();
        assertThat(facturierList).hasSize(databaseSizeBeforeUpdate);
        Facturier testFacturier = facturierList.get(facturierList.size() - 1);
        assertThat(testFacturier.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFacturier.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testFacturier.getStreetAddress()).isEqualTo(UPDATED_STREET_ADDRESS);
        assertThat(testFacturier.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testFacturier.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testFacturier.getStateProvince()).isEqualTo(UPDATED_STATE_PROVINCE);
    }

    @Test
    @Transactional
    public void updateNonExistingFacturier() throws Exception {
        int databaseSizeBeforeUpdate = facturierRepository.findAll().size();

        // Create the Facturier

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFacturierMockMvc.perform(put("/api/facturiers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(facturier)))
            .andExpect(status().isCreated());

        // Validate the Facturier in the database
        List<Facturier> facturierList = facturierRepository.findAll();
        assertThat(facturierList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFacturier() throws Exception {
        // Initialize the database
        facturierRepository.saveAndFlush(facturier);
        int databaseSizeBeforeDelete = facturierRepository.findAll().size();

        // Get the facturier
        restFacturierMockMvc.perform(delete("/api/facturiers/{id}", facturier.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Facturier> facturierList = facturierRepository.findAll();
        assertThat(facturierList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Facturier.class);
        Facturier facturier1 = new Facturier();
        facturier1.setId(1L);
        Facturier facturier2 = new Facturier();
        facturier2.setId(facturier1.getId());
        assertThat(facturier1).isEqualTo(facturier2);
        facturier2.setId(2L);
        assertThat(facturier1).isNotEqualTo(facturier2);
        facturier1.setId(null);
        assertThat(facturier1).isNotEqualTo(facturier2);
    }
}
