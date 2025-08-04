package com.mayank.mutualFund.emailService.service.consumer;

import com.mayank.mutualFund.emailService.payload.EmailPayload;
import com.mayank.mutualFund.emailService.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailTopicConsumer {
    private final EmailService emailService;

    public EmailTopicConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "Email",groupId = "myGroup",containerFactory = "kafkaListenerContainerFactory")
    public void consumer(EmailPayload emailPayload) throws MessagingException {
        log.info("Consumed msg:{}",emailPayload.toString());
        emailService.SendEmail(emailPayload.getTo(),emailPayload.getSubject(), emailPayload.getMessage());
    }
}
