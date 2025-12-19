package com.github.novabank.application.dtos;

import java.math.BigDecimal;

public class PaymentResult {

    private final String email;
    private final String paymentFrom;
    private final String paymentTo;
    private final BigDecimal amount;
    private final String paymentType;

    private final int payerUpdatedBalanceCents;
    private final int creditUpdatedBalanceCents;

    public PaymentResult(
            String email,
            String paymentFrom,
            String paymentTo,
            BigDecimal amount,
            String paymentType,
            int payerUpdatedBalanceCents,
            int creditUpdatedBalanceCents
    ) {
        this.email = email;
        this.paymentFrom = paymentFrom;
        this.paymentTo = paymentTo;
        this.amount = amount;
        this.paymentType = paymentType;
        this.payerUpdatedBalanceCents = payerUpdatedBalanceCents;
        this.creditUpdatedBalanceCents = creditUpdatedBalanceCents;
    }

    public String getEmail() { return email; }
    public String getPaymentFrom() { return paymentFrom; }
    public String getPaymentTo() { return paymentTo; }
    public BigDecimal getAmount() { return amount; }
    public String getPaymentType() { return paymentType; }

    public int getPayerUpdatedBalanceCents() { return payerUpdatedBalanceCents; }
    public int getCreditUpdatedBalanceCents() { return creditUpdatedBalanceCents; }
}
