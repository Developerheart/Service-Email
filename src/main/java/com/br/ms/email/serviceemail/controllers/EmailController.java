package com.br.ms.email.serviceemail.controllers;


import com.br.ms.email.serviceemail.dtos.EmailRequest;
import com.br.ms.email.serviceemail.models.EmailModel;
import com.br.ms.email.serviceemail.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailRequest emailRequest){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRequest, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

}
