package com.github.novabank.presentation.dtos;

import java.util.Objects;

/**
 * Represents a registration request in the application layer.
 */
public class RegisterRequest {

    private final String fullName;
    private final String email;
    private final String password;
    private final int age;
    private final String accountType;

    public RegisterRequest(String fullName, String email, String password, int age, String accountType) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Full name is required");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Valid email is required");
        }
        if (password == null || password.isBlank() || password.length() < 8) {
            throw new IllegalArgumentException("Password is required and must be at least 8 characters");
        }
        if (age < 1) {
            throw new IllegalArgumentException("Age must be at least 1");
        }
        if (accountType == null || accountType.isBlank()) {
            throw new IllegalArgumentException("Account type is required");
        }

        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.accountType = accountType;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getAccountType() {
        return accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegisterRequest)) return false;
        RegisterRequest that = (RegisterRequest) o;
        return age == that.age &&
                fullName.equals(that.fullName) &&
                email.equals(that.email) &&
                password.equals(that.password) &&
                accountType.equals(that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, email, password, age, accountType);
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
