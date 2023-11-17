package com.geoByte.GeoByteDelivery.data.repository;

import com.geoByte.GeoByteDelivery.data.model.DeliveryLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryLocationRepository extends JpaRepository<DeliveryLocation, Long> {
}
