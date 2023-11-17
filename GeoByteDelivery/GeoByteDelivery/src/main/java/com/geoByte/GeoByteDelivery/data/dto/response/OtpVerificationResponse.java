package com.geoByte.GeoByteDelivery.data.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OtpVerificationResponse {
    private String name;
    private String email;
    private boolean isEnabled;
    private JwtResponse jwtResponse;

}
