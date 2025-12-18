package com.github.novabank.presentation.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * AccountHistoryDTO is a Data Transfer Object used to request
 * account history information for a specific user.
 *
 * This DTO is typically used when the client requests
 * transaction or account activity history based on a username.
 */
public class AccountHistoryDTO {

    /**
     * The username of the account holder.
     * <p>
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Username is required")
    private String username;

    /**
     * Returns the username.
     *
     * @return the username of the account holder
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username of the account holder
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
