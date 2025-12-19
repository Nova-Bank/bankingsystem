package com.github.novabank.presentation.dtos;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a payment or transfer request in the application layer.
 */
public class PaymentRequest {

    private final String email;
    private final String paymentFrom;
    private final String paymentTo;
    private final BigDecimal amount;
    private final String paymentType;

    public PaymentRequest(String email, String paymentFrom, String paymentTo,
                          BigDecimal amount, String paymentType) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (paymentFrom == null || paymentFrom.isBlank()) {
            throw new IllegalArgumentException("Payment source is required");
        }
        if (paymentTo == null || paymentTo.isBlank()) {
            throw new IllegalArgumentException("Payment destination is required");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (paymentType == null || paymentType.isBlank()) {
            throw new IllegalArgumentException("Payment type is required");
        }

        this.email = email;
        this.paymentFrom = paymentFrom;
        this.paymentTo = paymentTo;
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public String getEmail() {
        return email;
    }

    public String getPaymentFrom() {
        return paymentFrom;
    }

    public String getPaymentTo() {
        return paymentTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentRequest)) return false;
        PaymentRequest that = (PaymentRequest) o;
        return email.equals(that.email) &&
               paymentFrom.equals(that.paymentFrom) &&
               paymentTo.equals(that.paymentTo) &&
               amount.equals(that.amount) &&
               paymentType.equals(that.paymentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, paymentFrom, paymentTo, amount, paymentType);
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "email='" + email + '\'' +
                ", paymentFrom='" + paymentFrom + '\'' +
                ", paymentTo='" + paymentTo + '\'' +
                ", amount=" + amount +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
