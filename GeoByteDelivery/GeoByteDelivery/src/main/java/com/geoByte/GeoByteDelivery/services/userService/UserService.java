package com.geoByte.GeoByteDelivery.services.userService;

import com.geoByte.GeoByteDelivery.data.dto.request.LoginRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.RegisterRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.VerifyEmailRequest;
import com.geoByte.GeoByteDelivery.data.dto.response.LoginResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.OtpVerificationResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.RegisterResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.UserResponse;
import com.geoByte.GeoByteDelivery.data.model.User;


public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);
    String resendVerificationMail(String email);
    OtpVerificationResponse verifyEmail(VerifyEmailRequest request);
    LoginResponse login(LoginRequest loginRequest);
    User getCurrentUser();
    UserResponse currentUserDto();
    User getUserByEmail(String email);
//    User getUserById(Long id);
}
