package com.github.novabank.presentation.dtos;

/**
 * AccountHistoryRequest represents the application-layer input
 * for fetching account history for a specific user.
 *
 * This is strictly for the domain/application logic, independent
 * of frontend requests.
 */
public class AccountHistoryRequest {

    private final String username;

    public AccountHistoryRequest(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

