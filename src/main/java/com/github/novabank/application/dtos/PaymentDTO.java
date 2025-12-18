package com.github.novabank.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * DTO for payment/transfer requests.
 */
public class PaymentDTO {

    /** Account email of the sender */
    @NotBlank(message = "Email is required")
    private String email;

    /** Source account ID or identifier */
    @NotBlank(message = "Payment source is required")
    private String paymentFrom;

    /** Destination account ID or identifier */
    @NotBlank(message = "Payment destination is required")
    private String paymentTo;

    /** Payment amount */
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    /** Type of payment (Transfer, Bill, etc.) */
    @NotBlank(message = "Payment type is required")
    private String paymentType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentFrom() {
        return paymentFrom;
    }

    public void setPaymentFrom(String paymentFrom) {
        this.paymentFrom = paymentFrom;
    }

    public String getPaymentTo() {
        return paymentTo;
    }

    public void setPaymentTo(String paymentTo) {
        this.paymentTo = paymentTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
