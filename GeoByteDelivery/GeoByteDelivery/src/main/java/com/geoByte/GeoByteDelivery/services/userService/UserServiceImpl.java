package com.geoByte.GeoByteDelivery.services.userService;

import com.geoByte.GeoByteDelivery.config.appSecurity.user.SecuredUser;
import com.geoByte.GeoByteDelivery.data.dto.request.LoginRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.RegisterRequest;
import com.geoByte.GeoByteDelivery.data.dto.request.VerifyEmailRequest;
import com.geoByte.GeoByteDelivery.data.dto.response.*;
import com.geoByte.GeoByteDelivery.data.model.User;
import com.geoByte.GeoByteDelivery.data.repository.UserRepository;
import com.geoByte.GeoByteDelivery.exception.GeoByteException;
import com.geoByte.GeoByteDelivery.exception.NotFoundException;
import com.geoByte.GeoByteDelivery.mail.MailService;
import com.geoByte.GeoByteDelivery.otp.OtpEntity;
import com.geoByte.GeoByteDelivery.otp.OtpService;
import com.geoByte.GeoByteDelivery.services.jwt.JwtTokenService;
import com.geoByte.GeoByteDelivery.utils.AppUtilities;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final MailService mailService;
    private final ModelMapper modelMapper;
    private final OtpService otpService;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public RegisterResponse register(RegisterRequest request) {
        checkIfUserExistsByEmail(request.getEmail());
        User user = modelMapper.map(request, User.class);
        String password = passwordEncoder.encode(request.getPassword());
        user.setPassword(password);
        User savedUser = userRepository.save(user);
        String otp = otpService.generateAndSaveOtp(savedUser);
        sendVerificationMail(savedUser, otp);
        return RegisterResponse.builder()
                .userId(savedUser.getId())
                .message("Check your mail for otp to activate your account")
                .mailIsSent(true)
                .build();
    }

    private void checkIfUserExistsByEmail(String email) {
        boolean isPresent = userRepository.findUserByEmail(email).isPresent();
        if(isPresent){
            User user = userRepository.findUserByEmail(email).get();
            if(!user.getIsEnable())
                resendVerificationMail(email);
            else throw new GeoByteException("User already exists");
        }
    }

    private void sendVerificationMail(User user, String otp){
        String mailTemplate = AppUtilities.GET_EMAIL_VERIFICATION_MAIL_TEMPLATE;
        String name = user.getName();
        String htmlContent = String.format(mailTemplate, name, otp);
        String subject = "Email Verification";
        String email = user.getEmail();
        mailService.sendMail(name, email, subject, htmlContent);
    }

    @Override
    public String resendVerificationMail(String email) {
        User user = getUserByEmail(email);
        String otp = otpService.generateAndSaveOtp(user);
        sendVerificationMail(user, otp);
        return "Another otp has sent to your mail proceed by checking your email";
    }

    @Override
    public OtpVerificationResponse verifyEmail(VerifyEmailRequest request) {
        Long userId = Long.parseLong(request.getUserId().trim());
        OtpEntity otpEntity = otpService.validateReceivedOtp(request.getOtp(), userId);
        User user = otpEntity.getUser();
        if(!user.getIsEnable()){
            user.setIsEnable(true);
            User savedUser = userRepository.save(user);
            otpService.deleteOtp(otpEntity);
            return getOtpVerificationResponse(savedUser);
        }
            throw new GeoByteException("User is already enabled");
    }

    private OtpVerificationResponse getOtpVerificationResponse(User user) {
        JwtResponse jwtResponse = jwtTokenService.getJwtTokenResponse(user);
        return OtpVerificationResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .jwtResponse(jwtResponse)
                .isEnabled(user.getIsEnable())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = jwtTokenService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        JwtResponse jwtResponse = jwtTokenService.getJwtTokenResponse(user);
        return LoginResponse.builder()
                .message("Login Successful")
                .jwtResponse(jwtResponse)
                .build();
    }

    @Override
    public User getCurrentUser() {
        try{
            final SecuredUser securedUser = (SecuredUser) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
            return securedUser.getUser();
        }catch (Exception ex){
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public UserResponse currentUserDto() {
        User user = getCurrentUser();
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }


//    @Override
//    public UserDTO currentUser() {
//        return new ModelMapper().map(
//                getCurrentUser(),
//                UserDTO.class
//        );
//    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                ()-> new NotFoundException("User not found"));
    }
//    @Override
//    public User getUserById(Long id) {
//        return userRepository.findById(id).orElseThrow(
//                ()-> new NotFoundException("User not found"));
//    }
}
