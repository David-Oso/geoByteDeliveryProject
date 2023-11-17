package com.geoByte.GeoByteDelivery.services.deliveryService;

import com.geoByte.GeoByteDelivery.data.dto.request.AddDeliveryLocationRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.CostOfDeliveryRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.UpdateDeliveryLocationRequest;
import com.geoByte.GeoByteDelivery.data.dto.response.CostOfDeliveryResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.DeliveryLocationResponse;
import com.geoByte.GeoByteDelivery.data.model.DeliveryLocation;
import com.geoByte.GeoByteDelivery.data.repository.DeliveryLocationRepository;
import com.geoByte.GeoByteDelivery.exception.NotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryLocationServiceImpl implements DeliveryLocationService {
    private final DeliveryLocationRepository deliveryLocationRepository;

    @PostConstruct
    private void addThreeDeliveryLocationsToRepository(){
        if(deliveryLocationRepository.count() == 0){
            DeliveryLocation deliveryLocation1 = new DeliveryLocation();
            deliveryLocation1.setLocationName("Berlin");
            deliveryLocation1.setLongitude(BigDecimal.valueOf(170.00));
            deliveryLocation1.setLatitude(BigDecimal.valueOf(70.00));
            deliveryLocation1.setClearingCost(generateClearingCost());

            DeliveryLocation deliveryLocation2 = new DeliveryLocation();
            deliveryLocation2.setLocationName("Hamburg");
            deliveryLocation2.setLongitude(BigDecimal.valueOf(165.00));
            deliveryLocation2.setLatitude(BigDecimal.valueOf(65.00));
            deliveryLocation2.setClearingCost(generateClearingCost());

            DeliveryLocation deliveryLocation3 = new DeliveryLocation();
            deliveryLocation3.setLongitude(BigDecimal.valueOf(160.00));
            deliveryLocation3.setLatitude(BigDecimal.valueOf(60.00));
            deliveryLocation3.setClearingCost(generateClearingCost());
            deliveryLocation3.setLocationName("Bremen");

            deliveryLocationRepository.save(deliveryLocation1);
            deliveryLocationRepository.save(deliveryLocation2);
            deliveryLocationRepository.save(deliveryLocation3);
        }
    }

    @Override
    public DeliveryLocationResponse addDeliveryLocation(AddDeliveryLocationRequest request) {
        DeliveryLocation deliveryLocation = new DeliveryLocation();
        deliveryLocation.setLocationName(request.getLocationName());
        deliveryLocation.setLatitude(request.getLatitude());
        deliveryLocation.setLongitude(request.getLongitude());
        deliveryLocation.setClearingCost(generateClearingCost());
        deliveryLocationRepository.save(deliveryLocation);
        return DeliveryLocationResponse.builder()
                .message("Delivery location added")
                .build();
    }

    private static BigDecimal generateClearingCost(){
        SecureRandom secureRandom = new SecureRandom();
        double randomDouble = 25 + secureRandom.nextDouble() * (100 -25 + 1);
        return BigDecimal.valueOf(randomDouble).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public DeliveryLocation getDeliveryLocationById(Long locationId) {
        return deliveryLocationRepository.findById(locationId).orElseThrow(
                ()-> new NotFoundException("Delivery location not found"));
    }

    @Override
    public DeliveryLocationResponse updateDeliveryLocation(UpdateDeliveryLocationRequest request) {
        DeliveryLocation deliveryLocation = getDeliveryLocationById(request.getDeliveryLocationId());
        deliveryLocation.setLocationName(request.getLocationName());
        deliveryLocation.setLatitude(request.getLatitude());
        deliveryLocation.setLongitude(request.getLongitude());
        deliveryLocationRepository.save(deliveryLocation);
        return DeliveryLocationResponse.builder()
                .message("Delivery location updated")
                .build();
    }

    @Override
    public List<DeliveryLocation> getAllDeliveryLocation() {
        return deliveryLocationRepository.findAll();
    }

    @Override
    public void deleteLocation(Long locationId) {
        deliveryLocationRepository.deleteById(locationId);
    }

    @Override
    public CostOfDeliveryResponse getCostOfDelivery(Long originId, Long destinationId) {
        DeliveryLocation origin = getDeliveryLocationById(originId);
        DeliveryLocation destination = getDeliveryLocationById(destinationId);

        BigDecimal costPerKilometer = BigDecimal.valueOf(1.00);

        BigDecimal x1 = origin.getLongitude();
        BigDecimal y1 = origin.getLatitude();
        BigDecimal x2 = destination.getLongitude();
        BigDecimal y2 = destination.getLatitude();

        BigDecimal distance = getDistance(y1, x1, y2, x2);
        BigDecimal distanceCost = distance.multiply(costPerKilometer);

        BigDecimal deliveryCost = distanceCost.add(destination.getClearingCost());

        return CostOfDeliveryResponse.builder()
                .message("Route is ....")
                .totalCost("Total cost of delivery:  $%s".formatted(deliveryCost))
                .build();
    }

    private static BigDecimal getDistance(BigDecimal y1, BigDecimal x1, BigDecimal y2, BigDecimal x2){
        BigDecimal y = y2.subtract(y1);
        BigDecimal x = x2.subtract(x1);
        return y.divide(x, 2, RoundingMode.HALF_UP);
    }

    @Override
    public Long getDeliveryLocationCount() {
        return deliveryLocationRepository.count();
    }
}
