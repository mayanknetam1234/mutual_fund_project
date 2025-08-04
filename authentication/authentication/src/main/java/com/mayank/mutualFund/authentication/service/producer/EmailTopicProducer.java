package com.mayank.mutualFund.authentication.service.producer;


import com.mayank.mutualFund.authentication.payload.EmailPayload;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmailTopicProducer {
    private final KafkaTemplate<String, EmailPayload> kafkaTemplate;

    public EmailTopicProducer(KafkaTemplate<String, EmailPayload> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishMessage(String to,String subject,String message){
        EmailPayload emailPayload=EmailPayload.builder()
                .to(to)
                .subject(subject)
                .message(message)
                .build();
        Message<EmailPayload> emailPayloadMessage= MessageBuilder
                .withPayload(emailPayload)
                .setHeader(KafkaHeaders.TOPIC,"Email")
                .build();

        kafkaTemplate.send(emailPayloadMessage);
    }
}
