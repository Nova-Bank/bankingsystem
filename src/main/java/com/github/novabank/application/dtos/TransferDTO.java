package com.github.novabank.application.dtos;

import java.time.LocalDateTime;

public class TransferDTO {

    private String sourceAccountId;
    private String targetAccountId;
    private double amount;
    private String currency;
    private LocalDateTime timestamp;
    private String status;

    public TransferDTO() {
        // default constructor for serialization
    }

    public TransferDTO(String sourceAccountId, String targetAccountId, double amount,
                       String currency, LocalDateTime timestamp, String status) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.status = status;
    }

    // Getters and setters
    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
