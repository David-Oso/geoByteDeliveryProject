package com.geoByte.GeoByteDelivery.mail;

import com.geoByte.GeoByteDelivery.mail.mailRequest.Recipient;
import com.geoByte.GeoByteDelivery.mail.mailRequest.SendEmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService{
    @Value("${mail.api.key}")
    private String apiKey;
    @Value("${brevo.mail.url}")
    private String mailUrl;

    @Async
    @Override
    public String sendMail(String name, String email, String subject, String htmlContent) {
        SendEmailRequest sendEmailRequest = new SendEmailRequest();
        Recipient recipient = new Recipient(name, email);
        sendEmailRequest.setSubject(subject);
        sendEmailRequest.setContent(htmlContent);
        sendEmailRequest.getRecipients().add(recipient);
        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("api-key", apiKey);
            httpHeaders.set("accept", APPLICATION_JSON_VALUE);
            RequestEntity<SendEmailRequest> entity =
                    new RequestEntity<>(sendEmailRequest, httpHeaders, HttpMethod.POST, URI.create(mailUrl));
            ResponseEntity<String> response = restTemplate.postForEntity(mailUrl, entity, String.class);
            log.info(":::::::::::::::::::EMAIL SENT SUCCESSFULLY:::::::::::::::::::");
            return response.getBody();
        }catch (Exception exception){
            throw new RuntimeException("Error sending mail");
        }

    }
}
