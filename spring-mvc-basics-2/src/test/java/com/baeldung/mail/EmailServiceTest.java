package com.baeldung.mail;

import com.baeldung.spring.configuration.ApplicationConfiguration;
import com.baeldung.spring.configuration.EmailConfiguration;
import com.baeldung.spring.mail.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes={ApplicationConfiguration.class, EmailConfiguration.class})
@WebAppConfiguration
public class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
    public void testSendSimpleMessageTemplate() {
        //To test that EmailConfiguration doesn't impact in the constructor. Just in the
        // the dependency injected
        String to = "claimdirectit@gmail.com";
        String subject = "subject";
        String text = "text";
        emailService.sendSimpleMessage(to, subject, text);
    }

}
