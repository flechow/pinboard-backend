package com.flechow.pinboard.service;

import com.flechow.pinboard.domain.Offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Offer}.
 */
public interface OfferService {

    /**
     * Save a offer.
     *
     * @param offer the entity to save.
     * @return the persisted entity.
     */
    Offer save(Offer offer);

    /**
     * Get all the offers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Offer> findAll(Pageable pageable);


    /**
     * Get the "id" offer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Offer> findOne(Long id);

    /**
     * Delete the "id" offer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
