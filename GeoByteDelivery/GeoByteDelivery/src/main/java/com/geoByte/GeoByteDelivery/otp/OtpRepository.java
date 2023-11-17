package com.geoByte.GeoByteDelivery.otp;

import com.geoByte.GeoByteDelivery.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
   OtpEntity findOtpEntityByUser(User user);
    OtpEntity findByOtpAndUser_Id(String otp, Long userId);
}
