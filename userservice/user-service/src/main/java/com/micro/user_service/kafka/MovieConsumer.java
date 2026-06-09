package com.micro.user_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MovieConsumer {
    @KafkaListener(topics = "movie-topic", groupId = "movie-group")
    public void consume(String message) {
        System.out.println("Received Message:" + message);
    }
}
