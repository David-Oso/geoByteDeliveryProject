package com.geoByte.GeoByteDelivery.data.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterResponse {
    private Long userId;
    private String message;
    private boolean mailIsSent;
}
