package br.com.compassuol.pb.challenge.notification.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_email")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fromEmail;
    private String fromName;
    private String replyTo;
    private String toEmail;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String body;
    private String contentType;
}