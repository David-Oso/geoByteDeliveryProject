package com.geoByte.GeoByteDelivery.data.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.geoByte.GeoByteDelivery.utils.ValidationUtilities.LOCATION_ID_NOT_NULL;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CostOfDeliveryRequest {

    @NotNull(message = LOCATION_ID_NOT_NULL)
    private Long originId;

    @NotNull(message = LOCATION_ID_NOT_NULL)
    private Long destinationId;
}
