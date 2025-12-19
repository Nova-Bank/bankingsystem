package com.github.novabank.presentation.dtos;

import java.util.Objects;

/**
 * Represents a credit score request in the application/domain layer.
 * This object is used for operations such as checking, refreshing,
 * or evaluating a user's credit score.
 */
public class CreditScoreRequest {

    private final String username;
    private final String requestType;

    public CreditScoreRequest(String username, String requestType) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (requestType == null || requestType.isBlank()) {
            throw new IllegalArgumentException("Request type is required");
        }

        this.username = username;
        this.requestType = requestType;
    }

    public String getUsername() {
        return username;
    }

    public String getRequestType() {
        return requestType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditScoreRequest)) return false;
        CreditScoreRequest that = (CreditScoreRequest) o;
        return username.equals(that.username) && requestType.equals(that.requestType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, requestType);
    }

    @Override
    public String toString() {
        return "CreditScoreRequest{" +
                "username='" + username + '\'' +
                ", requestType='" + requestType + '\'' +
                '}';
    }
}

