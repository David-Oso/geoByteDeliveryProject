package com.geoByte.GeoByteDelivery.services.userService;

import com.geoByte.GeoByteDelivery.data.dto.request.LoginRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.RegisterRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.VerifyEmailRequest;
import com.geoByte.GeoByteDelivery.data.dto.response.LoginResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.OtpVerificationResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.RegisterResponse;
import com.geoByte.GeoByteDelivery.data.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {
    @Autowired UserService userService;
    private RegisterRequest registerRequest;

    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setName("Staff");
        registerRequest.setEmail("temx@bikedid.com");
        registerRequest.setPassword("Password123$");
    }

    @Test
    void registerTest() {
        RegisterResponse registerResponse = userService.register(registerRequest);
        assertThat(registerResponse.getMessage()).isEqualTo("Check your mail for otp to activate your account");
    }

    @Test
    void verifyEmailTest() {
        VerifyEmailRequest request = new VerifyEmailRequest();
        request.setOtp("87124");
        request.setUserId("1");

        OtpVerificationResponse response = userService.verifyEmail(request);
        assertThat(response.getEmail()).isEqualTo(registerRequest.getEmail());
        assertThat(response.getName()).isEqualTo(registerRequest.getName());
        assertThat(response.getJwtResponse()).isNotNull();
    }

    @Test
    void loginTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("temx@bikedid.com");
        loginRequest.setPassword("Password123$");
        LoginResponse loginResponse = userService.login(loginRequest);
        assertThat(loginResponse.getMessage()).isEqualTo("Login Successful");
    }

    @Test
    void getUserByEmailTest(){
        User user = userService.getUserByEmail("temx@bikedid.com");
        assertThat(user.getName()).isEqualTo(registerRequest.getName());
    }
}
