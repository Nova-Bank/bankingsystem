package com.github.novabank.application.financal_actions;

import com.github.novabank.domain.finance.FinanceRepository;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;

public class ApplyInterest {

    private final FinanceRepository financeRepository;

    public ApplyInterest(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    public void execute(String accountUid, FinanceType type) {
        Finance finance = financeRepository.find(accountUid, type)
                .orElseThrow(() -> new IllegalStateException("Finance not found for accountUid=" + accountUid + " type=" + type));

        finance.interest();
        financeRepository.save(accountUid, type, finance);
    }
}
