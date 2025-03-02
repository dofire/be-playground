package com.dofire.producer;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // API to send messages to Kafka
    @PostMapping("/send")
    public String sendMessage(@RequestParam String message) {
        kafkaTemplate.send("test-topic", message);
        return "Message sent: " + message;
    }
}

