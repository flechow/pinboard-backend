package com.flechow.pinboard.repository;

import com.flechow.pinboard.domain.LatLng;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LatLng entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LatLngRepository extends JpaRepository<LatLng, Long> {
}
