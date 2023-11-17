package com.geoByte.GeoByteDelivery.controller;

import com.geoByte.GeoByteDelivery.data.dto.request.AddDeliveryLocationRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.UpdateDeliveryLocationRequest;
import com.geoByte.GeoByteDelivery.data.dto.response.CostOfDeliveryResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.DeliveryLocationResponse;
import com.geoByte.GeoByteDelivery.data.model.DeliveryLocation;
import com.geoByte.GeoByteDelivery.services.deliveryService.DeliveryLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/delivery_location/")
@RequiredArgsConstructor
public class DeliveryLocationController {
    private final DeliveryLocationService deliveryLocationService;

    @PostMapping("add")
    public ResponseEntity<?> addDeliveryLocation(@Valid @RequestBody AddDeliveryLocationRequest request){
        DeliveryLocationResponse response = deliveryLocationService.addDeliveryLocation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateDeliveryLocation(@Valid @RequestBody UpdateDeliveryLocationRequest request){
        DeliveryLocationResponse response = deliveryLocationService.updateDeliveryLocation(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("get/all")
    public ResponseEntity<?> getAllDeliveryLocations(){
        List<DeliveryLocation> deliveryLocations = deliveryLocationService.getAllDeliveryLocation();
        return ResponseEntity.ok(deliveryLocations);
    }

    @GetMapping("cost_of_delivery/get")
    public ResponseEntity<?> getCostOfDelivery(@RequestParam Long originId, Long destinationId){
        CostOfDeliveryResponse response = deliveryLocationService.getCostOfDelivery(originId, destinationId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDeliveryLocation(@PathVariable Long id){
        deliveryLocationService.deleteLocation(id);
        return ResponseEntity.ok("Delivery location deleted successfully");
    }
}