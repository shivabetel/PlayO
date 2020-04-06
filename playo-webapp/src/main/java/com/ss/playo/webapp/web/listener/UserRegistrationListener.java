package com.ss.playo.webapp.web.listener;

import com.ss.playo.webapp.web.events.OnRegistrationCompleteEvent;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class UserRegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private IVerificationTokenService verificationTokenService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        User user = event.getUser();
        if(user != null){
            String token = UUID.randomUUID().toString();
            verificationTokenService.createVerificationToken(user, token);
            String recipientAddress = user.getEmailId();
            String subject = "Registration Confirmation";
            simpleMailMessage.setTo(recipientAddress);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setFrom("shiva.betel@gmail.com");
            simpleMailMessage.setText("In order to complete the registration, please click the below link to confirm \n http://localhost:3000/registerConfirmation?token="+token);

            //TODO set text
            javaMailSender.send(simpleMailMessage);

        }


    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
