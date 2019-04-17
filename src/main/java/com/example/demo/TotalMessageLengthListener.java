package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TotalMessageLengthListener {

    private int totalMessageLength = 0;

    @KafkaListener(topics = "topic", groupId = "consumerGroup1")
    public void listen(String message) {
        totalMessageLength += message.length();
    }

    public int getTotalMessageLength() {
        return totalMessageLength;
    }
}
