package com.github.novabank.presentation.dto;

/**
 * Data Transfer Object for login requests.
 * Represents credentials sent from the frontend.
 */
public class LoginDTO {

    private String email;
    private String password;

    public LoginDTO() {
        // Required for JSON deserialization
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
