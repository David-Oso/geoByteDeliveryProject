package com.geoByte.GeoByteDelivery.services.deliveryService;

import com.geoByte.GeoByteDelivery.data.dto.request.AddDeliveryLocationRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.CostOfDeliveryRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.UpdateDeliveryLocationRequest;
import com.geoByte.GeoByteDelivery.data.dto.response.CostOfDeliveryResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.DeliveryLocationResponse;
import com.geoByte.GeoByteDelivery.data.model.DeliveryLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DeliveryLocationServiceImplTest {
    @Autowired DeliveryLocationService deliveryLocationService;
    private AddDeliveryLocationRequest addDeliveryLocationRequest;

    @BeforeEach
    void setUp() {
        addDeliveryLocationRequest = new AddDeliveryLocationRequest();
        addDeliveryLocationRequest.setLocationName("Manny");
        addDeliveryLocationRequest.setLongitude(BigDecimal.valueOf(70.00));
        addDeliveryLocationRequest.setLatitude(BigDecimal.valueOf(80.00));
    }

    @Test
    void addDeliveryLocation() {
        DeliveryLocationResponse response = deliveryLocationService.addDeliveryLocation(addDeliveryLocationRequest);
        assertThat(response.getMessage()).isEqualTo("Delivery location added");
//        since 3 locations are already in the database before adding any other one, the number of the locations in the data base will be 4
        assertThat(deliveryLocationService.getDeliveryLocationCount()).isEqualTo(4L);
    }

    @Test
    void getDeliveryLocationById() {
        DeliveryLocation deliveryLocation = deliveryLocationService.getDeliveryLocationById(4L);
        assertThat(deliveryLocation.getLocationName()).isEqualTo(addDeliveryLocationRequest.getLocationName());
        assertThat(deliveryLocation.getLatitude()).isEqualTo(addDeliveryLocationRequest.getLatitude());
        assertThat(deliveryLocation.getLongitude()).isEqualTo(addDeliveryLocationRequest.getLongitude());
    }

    @Test
    void updateDeliveryLocation() {
        UpdateDeliveryLocationRequest updateDeliveryLocationRequest = new UpdateDeliveryLocationRequest();
        updateDeliveryLocationRequest.setDeliveryLocationId(4L);
        updateDeliveryLocationRequest.setLocationName("Beyond");
//        updateDeliveryLocationRequest.setLatitude(60.00);
//        updateDeliveryLocationRequest.setLongitude(80.00);

        DeliveryLocationResponse deliveryLocationResponse = deliveryLocationService.updateDeliveryLocation(updateDeliveryLocationRequest);
        assertThat(deliveryLocationResponse.getMessage()).isEqualTo("Delivery location updated");

        DeliveryLocation deliveryLocation = deliveryLocationService.getDeliveryLocationById(4L);
        assertThat(deliveryLocation.getLocationName()).isEqualTo("Beyond");
//        assertThat(deliveryLocation.getLatitude()).isEqualTo(60.00);
//        assertThat(deliveryLocation.getLongitude()).isEqualTo(80.00);
    }

    @Test
    void getAllDeliveryLocation() {
        assertThat(deliveryLocationService.getDeliveryLocationCount()).isEqualTo(4L);
    }

    @Test
    void getCostOfDeliveryTest(){
        CostOfDeliveryResponse response = deliveryLocationService.getCostOfDelivery(4L, 1L);
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getTotalCost()).isNotNull();
    }
    @Test
    void deleteLocation() {
        deliveryLocationService.deleteLocation(1L);
        assertThat(deliveryLocationService.getDeliveryLocationCount()).isEqualTo(3L);
    }
}