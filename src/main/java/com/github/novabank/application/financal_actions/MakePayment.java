package com.github.novabank.application.services;

import com.github.novabank.application.dtos.PaymentResult;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;
import com.github.novabank.application.financal_actions.MakePayment;
import com.github.novabank.presentation.dtos.PaymentRequest;

import java.math.BigDecimal;

public class PaymentApplicationService {

    private final MakePayment makePayment;

    public PaymentApplicationService(MakePayment makePayment) {
        this.makePayment = makePayment;
    }

    public PaymentResult handle(PaymentRequest request) {

        // Convert money safely
        int amountCents = request.getAmount()
                .multiply(BigDecimal.valueOf(100))
                .intValueExact();

        // TEMP assumption: paymentFrom maps to account UID
        // If this mapping changes later, ONLY this class changes
        String payerAccountUid = request.getPaymentFrom();
        String creditOwnerAccountUid = request.getPaymentTo();

        int updatedCreditBalance = makePayment.execute(
                payerAccountUid,
                FinanceType.CHEQUING,
                creditOwnerAccountUid,
                amountCents
        );

        return new PaymentResult(
                request.getEmail(),
                request.getPaymentFrom(),
                request.getPaymentTo(),
                request.getAmount(),
                request.getPaymentType(),
                updatedCreditBalance
        );
    }
}
