package com.geoByte.GeoByteDelivery.services.jwt;

import com.geoByte.GeoByteDelivery.config.appSecurity.jwtToken.GeoToken;
import com.geoByte.GeoByteDelivery.config.appSecurity.jwtToken.GeoTokenService;
import com.geoByte.GeoByteDelivery.config.appSecurity.services.JwtService;
import com.geoByte.GeoByteDelivery.data.dto.response.JwtResponse;
import com.geoByte.GeoByteDelivery.data.model.User;
import com.geoByte.GeoByteDelivery.data.repository.UserRepository;
import com.geoByte.GeoByteDelivery.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService{
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final GeoTokenService eBankTokenService;

    @Override
    public User authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        String userEmail = authentication.getName();
        return getAppUserByEmail(userEmail);
    }

    private User getAppUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                ()-> new NotFoundException("User not found"));
    }

    @Override
    public JwtResponse getJwtTokenResponse(User user){
        final String email = user.getEmail();
        final String accessToken = jwtService.generateAccessToken(email);
        final String refreshToken = jwtService.generateRefreshToken(email);
        saveEBankToken(user, accessToken);
        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveEBankToken(User user, String accessToken) {
        final GeoToken eBankToken = GeoToken.builder()
                .accessToken(accessToken)
                .appUser(user)
                .isExpired(false)
                .isRevoked(false)
                .build();
        eBankTokenService.saveToken(eBankToken);
    }
}
