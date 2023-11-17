package com.geoByte.GeoByteDelivery.services.deliveryService;

import com.geoByte.GeoByteDelivery.data.dto.request.AddDeliveryLocationRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.CostOfDeliveryRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.UpdateDeliveryLocationRequest;
import com.geoByte.GeoByteDelivery.data.dto.response.CostOfDeliveryResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.DeliveryLocationResponse;
import com.geoByte.GeoByteDelivery.data.model.DeliveryLocation;

import java.util.List;

public interface DeliveryLocationService {

    DeliveryLocationResponse addDeliveryLocation(AddDeliveryLocationRequest request);
    DeliveryLocation getDeliveryLocationById(Long locationId);
    CostOfDeliveryResponse getCostOfDelivery(Long originId, Long destinationId);
    List<DeliveryLocation> getAllDeliveryLocation();
    DeliveryLocationResponse updateDeliveryLocation(UpdateDeliveryLocationRequest request);
    void deleteLocation(Long locationId);

    Long getDeliveryLocationCount();


}
