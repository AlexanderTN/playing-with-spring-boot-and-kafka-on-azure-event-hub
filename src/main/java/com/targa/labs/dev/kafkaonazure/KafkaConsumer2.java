package com.targa.labs.dev.kafkaonazure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer2 {

    @KafkaListener(topics = "${demo2.topic.name}", groupId="demo2")
    public void receive(SimpleMessage consumerMessage) {
        log.info("Received message from kafka queue: {}", consumerMessage.getBody());
    }
}
