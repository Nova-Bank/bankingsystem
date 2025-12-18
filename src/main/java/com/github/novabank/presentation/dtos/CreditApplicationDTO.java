package com.github.novabank.presentation.dtos;

public class CreditApplicationDTO {
    private String username;
    private String applicationType;
    private int requestedLimit;
    private String cardType;

    public CreditApplicationDTO() {}
    public CreditApplicationDTO(String username, String applicationType, int requestedLimit, String cardType) {
        this.username = username;
        this.applicationType = applicationType;
        this.requestedLimit = requestedLimit;
        this.cardType = cardType;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getApplicationType() { return applicationType; }
    public void setApplicationType(String applicationType) { this.applicationType = applicationType; }
    public int getRequestedLimit() { return requestedLimit; }
    public void setRequestedLimit(int requestedLimit) { this.requestedLimit = requestedLimit; }
    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }
}

