package com.github.novabank.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * DTO for submitting a credit/credit card application.
 */
public class CreditApplicationDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String applicationType;

    @NotNull
    @Positive
    private BigDecimal requestedLimit;

    @NotBlank
    private String cardType;

    // Optional: starting balance for pre-filled applications
    @Positive
    private BigDecimal startingBalance;

    // Optional: account/user ID to associate this application
    private String accountId;

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getApplicationType() { return applicationType; }
    public void setApplicationType(String applicationType) { this.applicationType = applicationType; }

    public BigDecimal getRequestedLimit() { return requestedLimit; }
    public void setRequestedLimit(BigDecimal requestedLimit) { this.requestedLimit = requestedLimit; }

    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }

    public BigDecimal getStartingBalance() { return startingBalance; }
    public void setStartingBalance(BigDecimal startingBalance) { this.startingBalance = startingBalance; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
}
