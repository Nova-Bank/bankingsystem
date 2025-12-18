package com.github.novabank.application.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * CreditScoreDTO is a Data Transfer Object used to request
 * credit score–related operations for a user.
 *
 * This DTO is typically used when the client requests
 * a credit score check or credit-related action based
 * on a username and request type.
 */
public class CreditScoreDTO {

    /**
     * The username of the account holder.
     * <p>
     * This field is required and cannot be blank.
     */
    @NotBlank
    private String username;

    /**
     * The type of credit score request being made.
     * <p>
     * This may represent actions such as checking,
     * refreshing, or evaluating a credit score.
     */
    @NotBlank
    private String requestType;

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

    /**
     * Returns the request type.
     *
     * @return the type of credit score request
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Sets the request type.
     *
     * @param requestType the type of credit score request
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
