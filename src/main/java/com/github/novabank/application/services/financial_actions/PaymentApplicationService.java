package com.github.novabank.application.services.financial_actions;

import com.github.novabank.application.dtos.PaymentResult;
import com.github.novabank.application.financal_actions.MakePayment;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;
import com.github.novabank.presentation.dtos.PaymentRequest;

import java.math.BigDecimal;

public class PaymentApplicationService {

    private final MakePayment makePayment;

    public PaymentApplicationService(MakePayment makePayment) {
        this.makePayment = makePayment;
    }

    public PaymentResult handle(PaymentRequest request) {

        // Convert the BigDecimal amount to cents for the domain use case
        int amountCents = request.getAmount()
                .multiply(BigDecimal.valueOf(100))
                .intValueExact();

        // You’re intentionally choosing CHEQUING for payer (per your current logic)
        String payerUid = request.getPaymentFrom();
        String creditOwnerUid = request.getPaymentTo();

        int updatedCreditBalanceCents = makePayment.execute(
                payerUid,
                FinanceType.CHEQUING, // payer account type
                creditOwnerUid,
                amountCents
        );

        // Build and return a presentation/output DTO
        return new PaymentResult(
                request.getEmail(),
                request.getPaymentFrom(),
                request.getPaymentTo(),
                request.getAmount(),
                request.getPaymentType(),
                updatedCreditBalanceCents, updatedCreditBalanceCents
        );
    }
}
