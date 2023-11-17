package com.geoByte.GeoByteDelivery.utils;

public class ValidationUtilities {
//    NAME
    public static final String NAME_NOT_BLANK = "Name cannot be blank";
    public static final String NAME_REGEX = "^[A-Z][a-zA-Z]{0,39}$";
    public static final String NAME_PATTERN_MESSAGE = "name must start with capital letter and it cant be only letters";

//    EMAIL
    public static final String EMAIL_NOT_BLANK = "Email cannot be blank";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static final String EMAIL_PATTERN_MESSAGE = "email is invalid";

//    PASSWORD
    public static final String PASSWORD_NOT_BLANK = "Password cannot be blank";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String PASSWORD_PATTERN_MESSAGE = "Password must contain at least one capital letter, one small letter, a number and special character(@$!%*?&)";

//   ADD_DELIVERY_LOCATION_REQUEST

//    delivery location id
    public static final String LOCATION_ID_NOT_NULL = "Delivery location id cannot be null";

//    location name
    public static final String LOCATION_NAME_REGEX = "^[A-Z][a-zA-Z]{2,150}$";
    public static final String LOCATION_NAME_PATTERN_MESSAGE = "Location name must be the minimum of 2 and the maximum of 150 ";
    public static final String LOCATION_NAME_NOT_BLANK = "Location name cannot be blank";

//    latitude
    public static final String LATITUDE_MAX = "90.0";
    public static final String LATITUDE_MIN = "-90.0";
    public static final String LATITUDE_MIN_MESSAGE ="Latitude must be greater or equal to -90.0";
    public static final String LATITUDE_MAX_MESSAGE ="Latitude must be less than or equal to 90.0";
    public static final String LATITUDE_CANNOT_BE_NULL= "Latitude cannot be null";

//    longitude
    public static final String LONGITUDE_MAX = "180.0";
    public static final String LONGITUDE_MIN = "-180.0";
    public static final String LONGITUDE_MIN_MESSAGE ="Longitude must be greater than or equal to -180.0";
    public static final String LONGITUDE_MAX_MESSAGE ="Longitude must be less than or equal to 180.0";
    public static final String LONGITUDE_CANNOT_BE_NULL= "Longitude cannot be null";
}

