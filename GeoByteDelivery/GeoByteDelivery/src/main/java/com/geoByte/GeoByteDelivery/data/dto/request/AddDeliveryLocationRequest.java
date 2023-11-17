package com.geoByte.GeoByteDelivery.data.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static com.geoByte.GeoByteDelivery.utils.ValidationUtilities.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddDeliveryLocationRequest {
    @NotBlank(message = LOCATION_NAME_NOT_BLANK)
    @Pattern(regexp = LOCATION_NAME_REGEX, message = LOCATION_NAME_PATTERN_MESSAGE)
    private String locationName;

    @NotNull(message = LATITUDE_CANNOT_BE_NULL  )
    @DecimalMin(value = LATITUDE_MIN, message = LATITUDE_MIN_MESSAGE)
    @DecimalMax(value = LATITUDE_MAX, message = LATITUDE_MAX_MESSAGE)
    private BigDecimal latitude;

    @NotNull(message = LONGITUDE_CANNOT_BE_NULL)
    @DecimalMin(value = LONGITUDE_MIN, message = LONGITUDE_MIN_MESSAGE)
    @DecimalMax(value = LONGITUDE_MAX, message = LONGITUDE_MAX_MESSAGE)
    private BigDecimal longitude;

}
