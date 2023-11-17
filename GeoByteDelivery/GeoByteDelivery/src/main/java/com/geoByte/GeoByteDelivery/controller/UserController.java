package com.geoByte.GeoByteDelivery.controller;

import com.geoByte.GeoByteDelivery.data.dto.request.LoginRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.RegisterRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.VerifyEmailRequest;
import com.geoByte.GeoByteDelivery.data.dto.response.LoginResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.OtpVerificationResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.RegisterResponse;
import com.geoByte.GeoByteDelivery.data.dto.response.UserResponse;
import com.geoByte.GeoByteDelivery.data.model.User;
import com.geoByte.GeoByteDelivery.services.userService.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@CrossOrigin(origins="http://localhost:3000")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){
        RegisterResponse response  = userService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("resend_verification_mail")
    public ResponseEntity<?> resendVerificationMail(@RequestParam @NotNull String email){
        String response = userService.resendVerificationMail(email);
        return ResponseEntity.ok(response);
    }
    @PostMapping("verify")
    public ResponseEntity<?> verifyEmail(@RequestBody VerifyEmailRequest request){
        OtpVerificationResponse response = userService.verifyEmail(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("current")
    public ResponseEntity<?> getCurrentUser(){
        UserResponse userResponse = userService.currentUserDto();
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("get/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam @NotNull String email){
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
