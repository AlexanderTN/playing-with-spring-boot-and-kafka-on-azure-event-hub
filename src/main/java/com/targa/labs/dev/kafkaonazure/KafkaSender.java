package com.targa.labs.dev.kafkaonazure;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//http://localhost:8080/kafka/publish/Hi%20Tam
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KafkaSender {

    private final KafkaProducer kafkaProducer;

    @PostMapping("send")
    public void sendData(@RequestBody SimpleMessage message) {
        this.kafkaProducer.send(message);
    }
}
