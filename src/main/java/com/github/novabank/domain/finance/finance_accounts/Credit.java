package com.github.novabank.domain.finance.finance_accounts;

import java.time.Clock;
import java.time.YearMonth;

import lombok.Getter;

@Getter
public class Credit extends Finance {
    private final int creditLimit;
    private final double creditInterestRate;
    private final int maximumBalanceWithoutInterest;

    private YearMonth lastInterestApplied;
    private final Clock clock;

    public Credit(
            int creditLimit,
            double creditInterestRate,
            int balance,
            int dailyWithdrawalLimit,
            int dailyPurchaseLimit,
            int dailyTransferLimit,
            int dailyTransferLimit2
    ) {
        super(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);

        if (creditLimit <= 0) throw new IllegalArgumentException("creditLimit must be > 0");
        if (creditInterestRate <= 0) throw new IllegalArgumentException("creditInterestRate must be > 0");
        if (dailyTransferLimit2 == null) throw new IllegalArgumentException("clock is required");

        this.creditLimit = creditLimit;
        this.creditInterestRate = creditInterestRate;
        this.maximumBalanceWithoutInterest = (int) Math.round(creditLimit * 0.10);

        this.clock = dailyTransferLimit2;
        this.lastInterestApplied = null;
    }


    @Override
    public void interest() {
        YearMonth now = YearMonth.now(clock);

        if (lastInterestApplied != null && now.equals(lastInterestApplied)) return;

        if (balance > maximumBalanceWithoutInterest) {
            balance = (int) Math.round(balance * (1 + creditInterestRate));
        }

        lastInterestApplied = now;
    }

    public void purchase(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be > 0");
        if (balance + amount > creditLimit) throw new IllegalStateException("Credit limit exceeded");
        balance += amount;
    }

    public void closeBalance(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be > 0");
        balance -= amount;
        if (balance < 0) balance = 0;
    }
}
