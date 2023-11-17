package com.geoByte.GeoByteDelivery.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class AppUtilities {
    public static final String COMPANY_NAME = "Geo Byte Delivery";
    public static final String COMPANY_EMAIL = "noreply@geoByte.com";

    private static final String EMAIL_VERIFICATION_TEMPLATE_LOCATION = "C:\\Users\\User\\Documents\\GeoByteDelivery\\GeoByteDelivery\\src\\main\\resources\\templates\\emailVerificationMail.html";
    private static String getTemplate(String templateLocation){
        try(BufferedReader reader =
                    new BufferedReader(new FileReader(templateLocation))){
            return reader.lines().collect(Collectors.joining());
        }catch (IOException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
    public static String GET_EMAIL_VERIFICATION_MAIL_TEMPLATE = getTemplate(EMAIL_VERIFICATION_TEMPLATE_LOCATION);

}
