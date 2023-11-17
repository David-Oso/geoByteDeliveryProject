package com.geoByte.GeoByteDelivery.config.appSecurity.jwtToken;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeoJwtTokenRepository extends JpaRepository<GeoToken, Long> {
//    @Query("""
//           select t from GeoJwtToken  t inner join User user\s
//           on t.appUser.id = user.id\s
//           where user.id = :id and (t.isExpired = false or t.isRevoked = false)
//           """)
//    List<GeoJwtToken> findAllValidTokenByUser(Long id);
    Optional<GeoToken> findGeoJwtTokenByAccessToken(String accessToken);
}
