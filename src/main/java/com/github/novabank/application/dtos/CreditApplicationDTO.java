package com.github.novabank.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * CreditApplicationDTO is a Data Transfer Object used to submit
 * a credit or credit card application for a user.
 *
 * This DTO carries all information required to evaluate
 * a credit request, including the requested limit and card type.
 */
public class CreditApplicationDTO {

    /**
     * The username of the applicant.
     * <p>
     * This field is required and cannot be blank.
     */
    @NotBlank
    private String username;

    /**
     * The type of credit application being submitted.
     * <p>
     * Examples may include "New Credit" or "Limit Increase".
     */
    @NotBlank
    private String applicationType;

    /**
     * The credit limit requested by the user.
     * <p>
     * This value must be positive and cannot be null.
     */
    @NotNull
    @Positive
    private BigDecimal requestedLimit;

    /**
     * The type of credit card being requested.
     * <p>
     * Examples may include "Visa", "Mastercard", or "Student".
     */
    @NotBlank
    private String cardType;

    /**
     * Returns the username of the applicant.
     *
     * @return the username of the applicant
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the applicant.
     *
     * @param username the username of the applicant
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the application type.
     *
     * @return the type of credit application
     */
    public String getApplicationType() {
        return applicationType;
    }

    /**
     * Sets the application type.
     *
     * @param applicationType the type of credit application
     */
    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    /**
     * Returns the requested credit limit.
     *
     * @return the requested credit limit
     */
    public BigDecimal getRequestedLimit() {
        return requestedLimit;
    }

    /**
     * Sets the requested credit limit.
     *
     * @param requestedLimit the requested credit limit
     */
    public void setRequestedLimit(BigDecimal requestedLimit) {
        this.requestedLimit = requestedLimit;
    }

    /**
     * Returns the credit card type.
     *
     * @return the credit card type
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the credit card type.
     *
     * @param cardType the credit card type
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
