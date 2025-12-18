package com.github.novabank.infrastructure.email;

/**
 * Service responsible for sending emails.
 * Implementation will be provided later.
 */
public interface EmailService {

    /**
     * Sends an email.
     *
     * @param to recipient
     * @param subject email subject
     * @param body email body
     */
    void send(String to, String subject, String body);
}
