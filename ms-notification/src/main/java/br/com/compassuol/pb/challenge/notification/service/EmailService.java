package br.com.compassuol.pb.challenge.notification.service;

import br.com.compassuol.pb.challenge.notification.entity.Email;
import br.com.compassuol.pb.challenge.notification.repository.EmailRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private EmailRepository emailRepository;
    private JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender){
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    public void sendEmail(Email email) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getFromEmail());
            message.setTo(email.getToEmail());
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            message.setReplyTo(email.getReplyTo());
            emailSender.send(message);
        }catch (MailException me){
            me.printStackTrace();
        }finally {
            emailRepository.save(email);
        }
    }
}