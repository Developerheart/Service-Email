package com.br.ms.email.serviceemail.services;


import com.br.ms.email.serviceemail.enums.StatusEmail;
import com.br.ms.email.serviceemail.models.EmailModel;
import com.br.ms.email.serviceemail.repositories.EmailReposiroty;
import org.hibernate.engine.internal.ImmutableEntityEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class EmailService {

    @Autowired
    private EmailReposiroty emailReposiroty;

    @Autowired
    private JavaMailSender eMailSender;

    public EmailModel sendEmail(EmailModel emailModel) {

        emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            eMailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);

        }
        catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);

        }finally {
            return emailReposiroty.save(emailModel);
        }
    }
}
