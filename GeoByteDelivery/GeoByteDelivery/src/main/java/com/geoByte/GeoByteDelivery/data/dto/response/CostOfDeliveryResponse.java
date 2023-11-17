package com.geoByte.GeoByteDelivery.data.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CostOfDeliveryResponse {
    private String message;
    private String totalCost;
}
