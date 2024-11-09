package com.example.ecom.notification.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @KafkaListener(topics = "email_topic", groupId = "default")
    void consumeEmailMessage(String message){
        System.out.println("Message received: "+message);
    }
}
