package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "topic", groupId = "dimi")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

}