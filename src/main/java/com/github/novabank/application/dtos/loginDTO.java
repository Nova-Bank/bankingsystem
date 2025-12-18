package com.github.novabank.presentation.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * LoginDTO is a Data Transfer Object used to receive
 * login credentials from the client (e.g., a login form).
 *
 * This class is typically used in authentication requests
 * to transfer a username and password from the frontend
 * to the backend.
 */
public class LoginDTO {

    /**
     * The username entered by the user.
     * <p>
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * The password entered by the user.
     * <p>
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Password is required")
    private String password;

    /**
     * Returns the username.
     *
     * @return the username provided by the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username provided by the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password.
     *
     * @return the password provided by the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password provided by the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
