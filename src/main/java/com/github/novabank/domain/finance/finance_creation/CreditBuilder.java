package com.github.novabank.domain.finance.finance_creation;

import com.github.novabank.domain.finance.finance_accounts.Credit;

import java.time.Clock;

public class CreditBuilder implements FinanceBuilder<Credit> {

    private int creditLimit;
    private double creditInterestRate;

    private int balance;
    private int amountSpentToday;

    private int dailyWithdrawalLimit;
    private int dailyPurchaseLimit;
    private int dailyTransferLimit;
    private int dailySpendingLimit;
    private Clock clock;

    public CreditBuilder setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
        return this;
    }
    public CreditBuilder setClock(Clock clock) {
        this.clock = clock;
        return this;

     CreditBuilder setCreditInterestRate(double creditInterestRate) {
        this.creditInterestRate = creditInterestRate;
        return this;
    }
     CreditBuilder setClock(Clock clock) {
            this.clock = clock;
            return this;

    CreditBuilder setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    CreditBuilder setAmountSpentToday(int amountSpentToday) {
        this.amountSpentToday = amountSpentToday;
        return this;
    }

    CreditBuilder setDailyWithdrawalLimit(int dailyWithdrawalLimit) {
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
        return this;
    }

    public CreditBuilder setDailyPurchaseLimit(int dailyPurchaseLimit) {
        this.dailyPurchaseLimit = dailyPurchaseLimit;
        return this;
    }

    CreditBuilder setDailyTransferLimit(int dailyTransferLimit) {
        this.dailyTransferLimit = dailyTransferLimit;
        return this;
    }

    CreditBuilder setDailySpendingLimit(int dailySpendingLimit) {
        this.dailySpendingLimit = dailySpendingLimit;
        return this;
    }

    @Override
    Credit build() {
        validate();
        return new Credit(
                creditLimit,
                creditInterestRate,
                balance,
                dailyWithdrawalLimit,
                dailyPurchaseLimit,
                dailyTransferLimit
        );
    }

    @Override
    public void validate() {
        if (balance < 0) throw new IllegalStateException("balance less than 0");
        if (amountSpentToday < 0) throw new IllegalStateException("amountSpentToday less than 0");

        if (creditLimit <= 0) throw new IllegalStateException("creditLimit less than or equal to 0");
        if (creditInterestRate <= 0) throw new IllegalStateException("creditInterestRate less than or equal to 0");

        if (dailyWithdrawalLimit <= 0) throw new IllegalStateException("dailyWithdrawalLimit less than or equal to 0");
        if (dailyPurchaseLimit <= 0) throw new IllegalStateException("dailyPurchaseLimit less than or equal to 0");
        if (dailyTransferLimit <= 0) throw new IllegalStateException("dailyTransferLimit less than or equal to 0");
        if (dailySpendingLimit <= 0) throw new IllegalStateException("dailySpendingLimit less than or equal to 0");
    }

    @Override
    public void reset() {
        this.creditLimit = 0;
        this.creditInterestRate = 0.0;

        this.balance = 0;
        this.amountSpentToday = 0;

        this.dailyWithdrawalLimit = 0;
        this.dailyPurchaseLimit = 0;
        this.dailyTransferLimit = 0;
        this.dailySpendingLimit = 0;
    }

    @Override
    public String toString() {
        return String.format(
                "CreditBuilder[creditLimit=%d, creditInterestRate=%.4f, balance=%d, amountSpentToday=%d, dailyWithdrawalLimit=%d, dailyPurchaseLimit=%d, dailyTransferLimit=%d, dailySpendingLimit=%d]",
                creditLimit,
                creditInterestRate,
                balance,
                amountSpentToday,
                dailyWithdrawalLimit,
                dailyPurchaseLimit,
                dailyTransferLimit,
                dailySpendingLimit
        );
    }
}
