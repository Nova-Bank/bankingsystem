package com.github.novabank.application.services.financal_actions;


import com.github.novabank.application.financal_actions.MakePayment;
import com.github.novabank.application.dtos.PaymentResult;
import com.github.novabank.presentation.dtos.PaymentRequest;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;

import java.math.BigDecimal;

public class PaymentApplicationService {

    private final MakePayment makePayment;

    public PaymentApplicationService(MakePayment makePayment) {
        this.makePayment = makePayment;
    }

    public PaymentResult handle(PaymentRequest request) {

        int amountCents = request.getAmount()
                .multiply(BigDecimal.valueOf(100))
                .intValueExact();

        int updatedCreditBalance = makePayment.execute(
                request.getPaymentFrom(),       // payer UID
                FinanceType.CHEQUING,            // payer type
                request.getPaymentTo(),          // credit owner UID
                amountCents
        );

        return new PaymentResult(
                request.getEmail(),
                request.getPaymentFrom(),
                request.getPaymentTo(),
                request.getAmount(),
                request.getPaymentType(),
                -1, // payer balance unknown unless domain returns it
                updatedCreditBalance
        );
    }
}

