package com.github.novabank.presentation.dtos;


import java.util.Objects;

/**
 * Represents a request to reset a user's password.
 * Contains only the data required by the application layer to perform the reset.
 */
public class ForgotPasswordRequest {

    private final String email;
    private final String newPassword;

    public ForgotPasswordRequest(String email, String newPassword) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (newPassword == null || newPassword.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }

        this.email = email;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ForgotPasswordRequest)) return false;
        ForgotPasswordRequest that = (ForgotPasswordRequest) o;
        return email.equals(that.email) && newPassword.equals(that.newPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, newPassword);
    }

    @Override
    public String toString() {
        return "ForgotPasswordRequest{" +
                "email='" + email + '\'' +
                ", newPassword='[PROTECTED]'" +
                '}';
    }
}

