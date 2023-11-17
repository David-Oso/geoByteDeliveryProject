package com.geoByte.GeoByteDelivery.services.jwt;

import com.geoByte.GeoByteDelivery.data.dto.response.JwtResponse;
import com.geoByte.GeoByteDelivery.data.model.User;

public interface JwtTokenService {
    User authenticate(String email, String password);
    JwtResponse getJwtTokenResponse(User user);
}
