package com.github.novabank.infrastructure.email;

import org.springframework.stereotype.Service;

@Service
public class ConsoleEmailService implements EmailService {

    @Override
    public void send(String to, String subject, String body) {
        System.out.println("--- Sending Email ---");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        System.out.println("---------------------");
    }
}
