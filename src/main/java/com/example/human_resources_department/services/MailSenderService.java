package com.example.human_resources_department.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Value("${spring.mail.username}")
    private String senderOfMail;

    private final JavaMailSender javaMailSender;

    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendByMail(String addressTo, String topic, String textMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(senderOfMail);
        mailMessage.setTo(addressTo);
        mailMessage.setSubject(topic);
        mailMessage.setText(textMessage);

        javaMailSender.send(mailMessage);
    }
}
