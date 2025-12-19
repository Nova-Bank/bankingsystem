package com.github.novabank.application.services.financial_actions;

import com.github.novabank.application.dtos.TransferResult;
import com.github.novabank.application.financal_actions.TransferUseCase;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;
import com.github.novabank.presentation.dtos.TransferRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Application service wrapping the transfer use case.
 * Accepts TransferRequest from the front-end and returns TransferResult DTO.
 */
@Service
public class TransferApplicationService {

    private final TransferUseCase transferUseCase;

    public TransferApplicationService(TransferUseCase transferUseCase) {
        this.transferUseCase = transferUseCase;
    }

    public TransferResult execute(TransferRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("TransferRequest cannot be null");
        }

        // Convert front-end request IDs into domain identifiers if needed
        // Here we assume sourceAccountId / targetAccountId can be used as UIDs
        String fromUid = String.valueOf(request.getSourceAccountId());
        String toUid = String.valueOf(request.getTargetAccountId());

        // Default FinanceType; adjust if you want dynamic types
        FinanceType fromType = FinanceType.CHEQUING;
        FinanceType toType = FinanceType.CHEQUING;

        boolean success = transferUseCase.execute(
                fromUid,
                fromType,
                toUid,
                toType,
                request.getAmount(),
                LocalDate.now()
        );

        // Map domain result -> TransferResult DTO
        TransferResult result = new TransferResult();
        result.setSourceAccountId(request.getSourceAccountId());
        result.setTargetAccountId(request.getTargetAccountId());
        result.setAmount(request.getAmount());
        result.setCurrency(request.getCurrency() != null ? request.getCurrency() : "CAD");
        result.setTimestamp(LocalDateTime.now());
        result.setStatus(success ? "COMPLETED" : "FAILED");

        return result;
    }
}
