package com.geoByte.GeoByteDelivery.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.geoByte.GeoByteDelivery.utils.ValidationUtilities.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = NAME_NOT_BLANK)
    @Pattern(regexp = NAME_REGEX, message = NAME_PATTERN_MESSAGE)
    private String name;

    @NotBlank(message = EMAIL_NOT_BLANK)
    @Email(regexp = EMAIL_REGEX, message = EMAIL_PATTERN_MESSAGE)
    private String email;

    @NotBlank(message = PASSWORD_NOT_BLANK)
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_PATTERN_MESSAGE)
    private String password;
}
