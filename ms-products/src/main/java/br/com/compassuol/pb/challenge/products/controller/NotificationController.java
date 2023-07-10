package br.com.compassuol.pb.challenge.products.controller;

import br.com.compassuol.pb.challenge.products.payload.EmailDTO;
import br.com.compassuol.pb.challenge.products.publisher.RabbitMQProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/notifications/")
public class NotificationController {
    private RabbitMQProducer producer;

    public NotificationController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> verifyContentType(@RequestBody @Valid EmailDTO emailDTO){
        producer.sendMessage(emailDTO);
        return ResponseEntity.ok("Type-RabbitMQ Email saved and sent");
    }
}