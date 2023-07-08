package br.com.compassuol.pb.challenge.notification.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_email")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String fromEmail;
    private String fromName;
    private String replyTo;
    private String toEmail;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String body;
    private String contentType;
}