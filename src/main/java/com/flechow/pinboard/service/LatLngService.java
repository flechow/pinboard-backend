package com.flechow.pinboard.service;

import com.flechow.pinboard.domain.LatLng;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link LatLng}.
 */
public interface LatLngService {

    /**
     * Save a latLng.
     *
     * @param latLng the entity to save.
     * @return the persisted entity.
     */
    LatLng save(LatLng latLng);

    /**
     * Get all the latLngs.
     *
     * @return the list of entities.
     */
    List<LatLng> findAll();


    /**
     * Get the "id" latLng.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LatLng> findOne(Long id);

    /**
     * Delete the "id" latLng.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
