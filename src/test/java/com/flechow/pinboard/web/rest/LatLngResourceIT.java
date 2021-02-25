package com.flechow.pinboard.web.rest;

import com.flechow.pinboard.PinboardApp;
import com.flechow.pinboard.domain.LatLng;
import com.flechow.pinboard.repository.LatLngRepository;
import com.flechow.pinboard.service.LatLngService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LatLngResource} REST controller.
 */
@SpringBootTest(classes = PinboardApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LatLngResourceIT {

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    @Autowired
    private LatLngRepository latLngRepository;

    @Autowired
    private LatLngService latLngService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLatLngMockMvc;

    private LatLng latLng;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LatLng createEntity(EntityManager em) {
        LatLng latLng = new LatLng()
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return latLng;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LatLng createUpdatedEntity(EntityManager em) {
        LatLng latLng = new LatLng()
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);
        return latLng;
    }

    @BeforeEach
    public void initTest() {
        latLng = createEntity(em);
    }

    @Test
    @Transactional
    public void createLatLng() throws Exception {
        int databaseSizeBeforeCreate = latLngRepository.findAll().size();
        // Create the LatLng
        restLatLngMockMvc.perform(post("/api/lat-lngs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(latLng)))
            .andExpect(status().isCreated());

        // Validate the LatLng in the database
        List<LatLng> latLngList = latLngRepository.findAll();
        assertThat(latLngList).hasSize(databaseSizeBeforeCreate + 1);
        LatLng testLatLng = latLngList.get(latLngList.size() - 1);
        assertThat(testLatLng.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testLatLng.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    public void createLatLngWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = latLngRepository.findAll().size();

        // Create the LatLng with an existing ID
        latLng.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLatLngMockMvc.perform(post("/api/lat-lngs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(latLng)))
            .andExpect(status().isBadRequest());

        // Validate the LatLng in the database
        List<LatLng> latLngList = latLngRepository.findAll();
        assertThat(latLngList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLatLngs() throws Exception {
        // Initialize the database
        latLngRepository.saveAndFlush(latLng);

        // Get all the latLngList
        restLatLngMockMvc.perform(get("/api/lat-lngs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(latLng.getId().intValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getLatLng() throws Exception {
        // Initialize the database
        latLngRepository.saveAndFlush(latLng);

        // Get the latLng
        restLatLngMockMvc.perform(get("/api/lat-lngs/{id}", latLng.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(latLng.getId().intValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingLatLng() throws Exception {
        // Get the latLng
        restLatLngMockMvc.perform(get("/api/lat-lngs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLatLng() throws Exception {
        // Initialize the database
        latLngService.save(latLng);

        int databaseSizeBeforeUpdate = latLngRepository.findAll().size();

        // Update the latLng
        LatLng updatedLatLng = latLngRepository.findById(latLng.getId()).get();
        // Disconnect from session so that the updates on updatedLatLng are not directly saved in db
        em.detach(updatedLatLng);
        updatedLatLng
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);

        restLatLngMockMvc.perform(put("/api/lat-lngs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLatLng)))
            .andExpect(status().isOk());

        // Validate the LatLng in the database
        List<LatLng> latLngList = latLngRepository.findAll();
        assertThat(latLngList).hasSize(databaseSizeBeforeUpdate);
        LatLng testLatLng = latLngList.get(latLngList.size() - 1);
        assertThat(testLatLng.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testLatLng.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    public void updateNonExistingLatLng() throws Exception {
        int databaseSizeBeforeUpdate = latLngRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLatLngMockMvc.perform(put("/api/lat-lngs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(latLng)))
            .andExpect(status().isBadRequest());

        // Validate the LatLng in the database
        List<LatLng> latLngList = latLngRepository.findAll();
        assertThat(latLngList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLatLng() throws Exception {
        // Initialize the database
        latLngService.save(latLng);

        int databaseSizeBeforeDelete = latLngRepository.findAll().size();

        // Delete the latLng
        restLatLngMockMvc.perform(delete("/api/lat-lngs/{id}", latLng.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LatLng> latLngList = latLngRepository.findAll();
        assertThat(latLngList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
