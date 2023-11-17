package com.geoByte.GeoByteDelivery.mail;

public interface MailService {
    String sendMail(String name, String email, String subject, String htmlContent);
}
