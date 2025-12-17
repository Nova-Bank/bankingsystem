package com.github.novabank.application.financal_actions;

import java.time.LocalDate;

import com.github.novabank.domain.finance.FinanceRepository;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_creation.FinanceType;

public class TransferUseCase {

    private final FinanceRepository financeRepository;

    public TransferUseCase(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    public boolean execute(
            String fromAccountUid,
            FinanceType fromType,
            String toAccountUid,
            FinanceType toType,
            int amountCents,
            LocalDate date
    ) {
        Finance from = financeRepository.find(fromAccountUid, fromType)
                .orElseThrow(() -> new IllegalStateException("From finance not found for accountUid=" + fromAccountUid + " type=" + fromType));

        Finance to = financeRepository.find(toAccountUid, toType)
                .orElseThrow(() -> new IllegalStateException("To finance not found for accountUid=" + toAccountUid + " type=" + toType));

        boolean ok = from.transfer(from, to, amountCents, date);

        financeRepository.save(fromAccountUid, fromType, from);
        financeRepository.save(toAccountUid, toType, to);

        return ok;
    }
}
