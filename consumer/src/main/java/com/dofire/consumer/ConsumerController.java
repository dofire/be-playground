package com.dofire.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    private final List<String> messages = new ArrayList<>();

    // Kafka Consumer: Listens for messages on "test-topic"
    @KafkaListener(topics = "test-topic", groupId = "group_id")
    public void listen(String message) {
        messages.add(message);
        System.out.println("Received message: " + message);
    }

    // API: Check the last received messages
    @GetMapping("/messages")
    public List<String> getMessages() {
        return messages;
    }

    // API: Check if the consumer is running
    @GetMapping("/status")
    public String status() {
        return "Consumer is running and listening to Kafka.";
    }
}
