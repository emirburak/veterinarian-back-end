package com.example.veterinarian.service.impl;

import com.example.veterinarian.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendSimpleEmail(String to, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("emirburak599@gmail.com");
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        mailSender.send(mail);
    }
}
