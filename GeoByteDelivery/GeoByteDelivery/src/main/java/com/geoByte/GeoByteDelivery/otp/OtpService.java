package com.geoByte.GeoByteDelivery.otp;

import com.geoByte.GeoByteDelivery.data.model.User;

public interface OtpService {
    String generateAndSaveOtp(User user);
    OtpEntity validateReceivedOtp(String otp, Long userId);
    void deleteOtp(OtpEntity otpEntity);
}
