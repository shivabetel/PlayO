package com.ss.playo.webapp.web.listener;

import com.ss.playo.webapp.web.events.OnRegistrationCompleteEvent;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;


@Component
public class UserRegistrationListener {

    private IVerificationTokenService verificationTokenService;

    private MessageSource messageSource;

    private JavaMailSender javaMailSender;

    public UserRegistrationListener(IVerificationTokenService verificationTokenService,
                                    MessageSource messageSource,
                                    JavaMailSender javaMailSender) {
        this.verificationTokenService = verificationTokenService;
        this.messageSource = messageSource;
        this.javaMailSender = javaMailSender;
    }

    @Async
    @EventListener
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
//            simpleMailMessage.setFrom("shiva.betel@gmail.com");
            simpleMailMessage.setText("In order to complete the registration, please click the below link to confirm \n http://localhost:3000/registerConfirmation?token="+token);

            //TODO set text
            javaMailSender.send(simpleMailMessage);

        }


    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
}
