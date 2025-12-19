package com.github.novabank.application.dtos;

import java.time.LocalDateTime;

/**
 * TransferDTO represents a money transfer between accounts.
 */
public class TransferResult {

    /** UID of the source account */
    private int sourceAccountId;

    /** UID of the target account */
    private int targetAccountId;

    /** Amount to transfer */
    private int amount; // int to match domain logic (Chequing/Credit)

    /** Optional currency, defaults to CAD */
    private String currency = "CAD";

    /** Timestamp of the transfer */
    private LocalDateTime timestamp;

    /** Status of the transfer */
    private String status; // Could be "PENDING", "COMPLETED", "FAILED"

    public TransferResult() {
        // default constructor for serialization
    }

    public TransferResult(int sourceAccountId, int targetAccountId, int amount,
                       String currency, LocalDateTime timestamp, String status) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.status = status;
    }

    public int getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(int sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public int getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(int targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

    public Object getUsername() {
    }

    public Object getFromAccount() {
    }

    public Object getToAccount() {
    }
}
