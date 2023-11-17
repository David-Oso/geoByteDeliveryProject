package com.geoByte.GeoByteDelivery.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest

class MailServiceImplTest {
    @Autowired MailService mailService;

    @Test
    void sendMailTest(){
        String name = "Temx";
//        Enter your own email
        String email = "temx@gmail.com";
        String subject = "Testing";
        String content = "<h1> This it just testing</h1>";

        String response = mailService.sendMail(name, email, subject, content);
        assertThat(response).isNotNull();
    }
}