package com.github.novabank.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ForgotPasswordDTO is a Data Transfer Object used to handle
 * password reset requests.
 *
 * This DTO is typically used when a user has forgotten their
 * password and wants to set a new one by providing their username.
 */
public class ForgotPasswordDTO {

    /**
     * The username of the account requesting a password reset.
     * <p>
     * This field is required and must be between 3 and 20 characters.
     */
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    /**
     * The new password the user wants to set.
     * <p>
     * This field is required and must be at least 8 characters long.
     */
    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String newPassword;

    /**
     * Returns the username.
     *
     * @return the username requesting the password reset
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username requesting the password reset
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the new password.
     *
     * @return the new password to be set
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the new password.
     *
     * @param newPassword the new password to be set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
