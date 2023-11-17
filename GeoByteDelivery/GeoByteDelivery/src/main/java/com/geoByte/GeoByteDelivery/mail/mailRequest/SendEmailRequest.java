package com.geoByte.GeoByteDelivery.mail.mailRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.geoByte.GeoByteDelivery.utils.AppUtilities.COMPANY_EMAIL;
import static com.geoByte.GeoByteDelivery.utils.AppUtilities.COMPANY_NAME;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SendEmailRequest {
    @JsonProperty("sender")
    private final Sender sender = new Sender(COMPANY_NAME, COMPANY_EMAIL);
    @JsonProperty("to")
    private List<Recipient> recipients = new ArrayList<>();
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("htmlContent")
    private String content;
}
