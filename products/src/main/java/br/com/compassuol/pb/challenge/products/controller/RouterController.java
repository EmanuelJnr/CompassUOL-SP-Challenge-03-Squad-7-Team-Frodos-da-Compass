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
@RequestMapping("/notifications")
public class RouterController {
    private RabbitMQProducer producer;

    public RouterController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> verifyContentType(@RequestBody @Valid EmailDTO emailDTO){
        if (emailDTO.getContentType().equals("html/plain")) {
            return sendEmailHttp(emailDTO);
        }
        return sendEmailRmq(emailDTO);
    }

    public ResponseEntity<String> sendEmailRmq(EmailDTO emailDTO){
        producer.sendMessage(emailDTO);
        return ResponseEntity.ok("Type-RabbitMQ Email saved and sent");
    }


    public ResponseEntity<String> sendEmailHttp(EmailDTO emailDTO) {

        String responseMSNotification = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(emailDTO);

            URL url = new URL("http://localhost:8081/sending-email");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.writeBytes(requestBody);
            }

            responseMSNotification = "Status of MS-Notification: " + connection.getResponseCode();
            try (BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                responseMSNotification += "\nBody of MS-Notification: " + bf.readLine();
            }
        } catch (IOException me){
            me.printStackTrace();
        }
        return new ResponseEntity<>(responseMSNotification, HttpStatus.OK);
    }
}