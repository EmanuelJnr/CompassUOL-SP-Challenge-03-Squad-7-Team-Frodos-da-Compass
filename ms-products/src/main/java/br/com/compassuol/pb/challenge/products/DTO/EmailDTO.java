package br.com.compassuol.pb.challenge.products.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDTO {
    @NotBlank
    @Email
    private String fromEmail;
    @NotBlank
    private String fromName;
    @NotBlank
    @Email
    private String replyTo;
    @NotBlank
    @Email
    private String toEmail;
    private String subject;
    @NotBlank
    private String body;
    @NotBlank
    private String contentType;
}