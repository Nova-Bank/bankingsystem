package com.github.novabank.presentation.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a transfer request in the application/domain layer.
 */
public class TransferRequest {

    private final int sourceAccountId;
    private final int targetAccountId;
    private final int amount;
    private final String currency;
    private final LocalDateTime timestamp;
    private final String status;

    public TransferRequest(int sourceAccountId,
                           int targetAccountId,
                           int amount,
                           String currency,
                           LocalDateTime timestamp,
                           String status) {
        if (sourceAccountId <= 0 || targetAccountId <= 0) {
            throw new IllegalArgumentException("Account IDs must be positive");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
        this.currency = currency != null ? currency : "CAD";
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
        this.status = status != null ? status : "PENDING";
    }

    public int getSourceAccountId() {
        return sourceAccountId;
    }

    public int getTargetAccountId() {
        return targetAccountId;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferRequest)) return false;
        TransferRequest that = (TransferRequest) o;
        return sourceAccountId == that.sourceAccountId &&
               targetAccountId == that.targetAccountId &&
               amount == that.amount &&
               currency.equals(that.currency) &&
               timestamp.equals(that.timestamp) &&
               status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceAccountId, targetAccountId, amount, currency, timestamp, status);
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "sourceAccountId=" + sourceAccountId +
                ", targetAccountId=" + targetAccountId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                '}';
    }
}
