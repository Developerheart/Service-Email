package com.br.ms.email.serviceemail.consumers;


import com.br.ms.email.serviceemail.dtos.EmailRequest;
import com.br.ms.email.serviceemail.models.EmailModel;
import com.br.ms.email.serviceemail.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {


    @Autowired
    private EmailService emailService;


    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailRequest emailRequest){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRequest, emailModel);
        emailService.sendEmail(emailModel);
        System.out.println("Email com Status: " + emailModel.getStatusEmail().toString());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
