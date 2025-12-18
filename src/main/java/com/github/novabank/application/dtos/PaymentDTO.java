package com.github.novabank.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * PaymentDTO is a Data Transfer Object used to submit
 * payment or transfer requests for a user.
 *
 * This DTO carries all required information to process
 * a payment between accounts or entities.
 */
public class PaymentDTO {

    /**
     * The username of the account holder initiating the payment.
     * <p>
     * This field is required and cannot be blank.
     */
    @NotBlank
    private String username;

    /**
     * The source of the payment.
     * <p>
     * This may represent an account, card, or balance
     * from which the payment is made.
     */
    @NotBlank
    private String paymentFrom;

    /**
     * The destination of the payment.
     * <p>
     * This may represent another account, biller,
     * or external recipient.
     */
    @NotBlank
    private String paymentTo;

    /**
     * The payment amount.
     * <p>
     * This value must be positive and cannot be null.
     */
    @NotNull
    @Positive
    private BigDecimal amount;

    /**
     * The type of payment being made.
     * <p>
     * Examples may include "Transfer", "Bill Payment",
     * or "Credit Card Payment".
     */
    @NotBlank
    private String paymentType;

    /**
     * Returns the username of the account holder.
     *
     * @return the username of the account holder
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the account holder.
     *
     * @param username the username of the account holder
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the source of the payment.
     *
     * @return the payment source
     */
    public String getPaymentFrom() {
        return paymentFrom;
    }

    /**
     * Sets the source of the payment.
     *
     * @param paymentFrom the payment source
     */
    public void setPaymentFrom(String paymentFrom) {
        this.paymentFrom = paymentFrom;
    }

    /**
     * Returns the destination of the payment.
     *
     * @return the payment destination
     */
    public String getPaymentTo() {
        return paymentTo;
    }

    /**
     * Sets the destination of the payment.
     *
     * @param paymentTo the payment destination
     */
    public void setPaymentTo(String paymentTo) {
        this.paymentTo = paymentTo;
    }

    /**
     * Returns the payment amount.
     *
     * @return the payment amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the payment amount.
     *
     * @param amount the payment amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Returns the payment type.
     *
     * @return the payment type
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the payment type.
     *
     * @param paymentType the payment type
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
