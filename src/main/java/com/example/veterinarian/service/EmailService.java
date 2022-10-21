package com.example.veterinarian.service;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String message);
}
