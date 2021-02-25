package com.flechow.pinboard.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.flechow.pinboard.web.rest.TestUtil;

public class LatLngTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LatLng.class);
        LatLng latLng1 = new LatLng();
        latLng1.setId(1L);
        LatLng latLng2 = new LatLng();
        latLng2.setId(latLng1.getId());
        assertThat(latLng1).isEqualTo(latLng2);
        latLng2.setId(2L);
        assertThat(latLng1).isNotEqualTo(latLng2);
        latLng1.setId(null);
        assertThat(latLng1).isNotEqualTo(latLng2);
    }
}
