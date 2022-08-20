package com.targa.labs.dev.kafkaonazure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer1 {

    @KafkaListener(topics = "${demo1.topic.name}", groupId="demo1")
    public void receive(SimpleMessage consumerMessage) {
        log.info("Received message from kafka queue: {}", consumerMessage.getBody());
    }
}
