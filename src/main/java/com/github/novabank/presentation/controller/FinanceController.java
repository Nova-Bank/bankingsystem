package com.github.novabank.presentation.controller;

import java.time.LocalDate;

import com.github.novabank.application.FinanceService;
import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.FinanceType;

public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    public int deposit(String accountUid, FinanceType type, int amountCents) {
        return financeService.deposit(accountUid, type, amountCents);
    }

    public int withdraw(String accountUid, FinanceType type, int amountCents) {
        return financeService.withdraw(accountUid, type, amountCents);
    }

    public boolean transfer(String fromAccountUid, FinanceType fromType,
                            String toAccountUid, FinanceType toType,
                            int amountCents, LocalDate date) {
        return financeService.transfer(fromAccountUid, fromType, toAccountUid, toType, amountCents, date);
    }

    public int getBalance(String accountUid, FinanceType type) {
        return financeService.getBalance(accountUid, type);
    }

    public void applyMonthlyInterest(String accountUid, FinanceType type) {
        financeService.applyInterest(accountUid, type);
    }

    public Finance getFinance(String accountUid, FinanceType type) {
        return financeService.getFinance(accountUid, type);
    }
}
