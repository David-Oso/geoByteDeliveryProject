package com.geoByte.GeoByteDelivery.otp;

import com.geoByte.GeoByteDelivery.data.model.User;
import com.geoByte.GeoByteDelivery.data.repository.UserRepository;
import com.geoByte.GeoByteDelivery.exception.GeoByteException;
import com.geoByte.GeoByteDelivery.exception.NotFoundException;
import com.geoByte.GeoByteDelivery.exception.OtpException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final OtpRepository otpRepository;

    @Override
    public String generateAndSaveOtp(User user) {
        OtpEntity existingOtp = otpRepository.findOtpEntityByUser(user);
        if(existingOtp != null)
            otpRepository.delete(existingOtp);
        String generatedOtp = generateOtp();
        OtpEntity otpEntity = OtpEntity.builder()
                .user(user)
                .otp(generatedOtp)
                .build();
        otpRepository.save(otpEntity);
        return generatedOtp;
    }
    private static String generateOtp(){
        SecureRandom secureRandom = new SecureRandom();
        return String.valueOf(secureRandom.nextInt(10000, 100000));
    }

    @Override
    public OtpEntity validateReceivedOtp(String otp, Long userId) {
        OtpEntity otpEntity = otpRepository.findByOtpAndUser_Id(otp, userId);
        if(otpEntity ==  null)
            throw new OtpException("Otp is invalid");
        else if (otpEntity.getExpirationTime().isBefore(LocalDateTime.now())){
            otpRepository.delete(otpEntity);
            throw new OtpException("Otp is expired");
        }
        return otpEntity;
    }

    @Override
    public void deleteOtp(OtpEntity otpEntity) {
        otpRepository.delete(otpEntity);
    }
}
