package com.geoByte.GeoByteDelivery.config.appSecurity.jwtToken;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GeoJwtTokenServiceImpl implements GeoTokenService {
    private final GeoJwtTokenRepository geoJwtTokenRepository;

    @Override
    public void saveToken(GeoToken geoToken) {
        geoJwtTokenRepository.save(geoToken);
    }

    @Override
    public Optional<GeoToken> getValidTokenByAnyToken(String accessToken) {
        return geoJwtTokenRepository.findGeoJwtTokenByAccessToken(accessToken);
    }

    @Override
    public void revokeToken(String accessToken) {
        final GeoToken geoToken = getValidTokenByAnyToken(accessToken)
                .orElse(null);
        if(geoToken != null){
            geoToken.setRevoked(true);
            geoJwtTokenRepository.save(geoToken);
        }
    }
}
