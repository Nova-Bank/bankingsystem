package com.github.novabank.presentation.dtos;

import java.math.BigDecimal;

/**
 * Represents a request to submit a credit/credit card application
 * for the domain/application layer.
 */
public class CreditApplication {

    private final String username;
    private final String applicationType;
    private final BigDecimal requestedLimit;
    private final String cardType;
    private final BigDecimal startingBalance; // optional
    private final String accountId;           // optional

    public CreditApplication(
            String username,
            String applicationType,
            BigDecimal requestedLimit,
            String cardType,
            BigDecimal startingBalance,
            String accountId
    ) {
        if (username == null || username.isBlank()) throw new IllegalArgumentException("Username is required");
        if (applicationType == null || applicationType.isBlank()) throw new IllegalArgumentException("Application type is required");
        if (requestedLimit == null || requestedLimit.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Requested limit must be positive");
        if (cardType == null || cardType.isBlank()) throw new IllegalArgumentException("Card type is required");

        this.username = username;
        this.applicationType = applicationType;
        this.requestedLimit = requestedLimit;
        this.cardType = cardType;
        this.startingBalance = startingBalance;
        this.accountId = accountId;
    }

    public String getUsername() { return username; }
    public String getApplicationType() { return applicationType; }
    public BigDecimal getRequestedLimit() { return requestedLimit; }
    public String getCardType() { return cardType; }
    public BigDecimal getStartingBalance() { return startingBalance; }
    public String getAccountId() { return accountId; }
}
