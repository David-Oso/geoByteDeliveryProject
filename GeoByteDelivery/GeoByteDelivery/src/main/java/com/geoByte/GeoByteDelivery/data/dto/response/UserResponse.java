package com.geoByte.GeoByteDelivery.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserResponse {
    private Long id;
    private String name;
    private String email;
}
