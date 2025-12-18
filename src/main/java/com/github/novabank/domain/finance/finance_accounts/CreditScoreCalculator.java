package com.github.novabank.domain.finance.finance_accounts;


public interface CreditScoreCalculator {
    int calculate(MonthlySnapshot snapshot);
}