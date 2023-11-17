package com.geoByte.GeoByteDelivery.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.geoByte.GeoByteDelivery.utils.ValidationUtilities.EMAIL_NOT_BLANK;
import static com.geoByte.GeoByteDelivery.utils.ValidationUtilities.PASSWORD_NOT_BLANK;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = EMAIL_NOT_BLANK)
    private String email;

    @NotBlank(message = PASSWORD_NOT_BLANK)
    private String password;
}
