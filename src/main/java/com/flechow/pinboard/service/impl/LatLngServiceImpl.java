package com.flechow.pinboard.service.impl;

import com.flechow.pinboard.service.LatLngService;
import com.flechow.pinboard.domain.LatLng;
import com.flechow.pinboard.repository.LatLngRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link LatLng}.
 */
@Service
@Transactional
public class LatLngServiceImpl implements LatLngService {

    private final Logger log = LoggerFactory.getLogger(LatLngServiceImpl.class);

    private final LatLngRepository latLngRepository;

    public LatLngServiceImpl(LatLngRepository latLngRepository) {
        this.latLngRepository = latLngRepository;
    }

    @Override
    public LatLng save(LatLng latLng) {
        log.debug("Request to save LatLng : {}", latLng);
        return latLngRepository.save(latLng);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LatLng> findAll() {
        log.debug("Request to get all LatLngs");
        return latLngRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LatLng> findOne(Long id) {
        log.debug("Request to get LatLng : {}", id);
        return latLngRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete LatLng : {}", id);
        latLngRepository.deleteById(id);
    }
}
