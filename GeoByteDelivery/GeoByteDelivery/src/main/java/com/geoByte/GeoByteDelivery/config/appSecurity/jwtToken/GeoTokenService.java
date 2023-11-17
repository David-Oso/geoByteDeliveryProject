package com.geoByte.GeoByteDelivery.config.appSecurity.jwtToken;

import java.util.Optional;

public interface GeoTokenService {
    void saveToken(GeoToken testingToken);
    Optional<GeoToken> getValidTokenByAnyToken(String anyToken);
    void revokeToken(String accessToken);
}
