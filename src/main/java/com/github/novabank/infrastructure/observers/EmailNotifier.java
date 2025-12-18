package com.github.novabank.infrastructure.observers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.novabank.infrastructure.email.EmailService;

/**
 * Orchestrates email notifications from API events.
 */
@Service
public class EmailNotifier {

    private static final Logger log =
            LoggerFactory.getLogger(EmailNotifier.class);

    private final EmailService emailService;

    public EmailNotifier(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Notifies user of successful login.
     *
     * @param email user email
     */
    public void notifyLoginSuccess(String email) {
        log.info("Sending login notification to {}", email);
        emailService.send(
                email,
                "Login Notification",
                "You have successfully logged in."
        );
    }
}
