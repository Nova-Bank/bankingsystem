package com.github.novabank.domain.finance.finance_accounts;

import lombok.Getter;
import lombok.Setter;

import java.time.Clock;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.Year;
@Getter
@Setter
public class Savings extends Finance {

    private final double interestRate;
    private YearMonth lastInterestApplied;
    private final Clock clock;

    public Savings(int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit, double interestRate, Clock clock) {
        super(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
        this.interestRate = interestRate;
        this.clock = clock;
        this.lastInterestApplied = null;
    }

    public Savings(int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit, double interestRate) {
        this(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit, interestRate,
                Clock.system(ZoneId.of("America/Toronto")));
    }

    @Override
    public void interest() {
        YearMonth now = YearMonth.now(clock);

        if (lastInterestApplied != null && now.equals(lastInterestApplied)) {
            return;
        }

        if (balance > 0) {
            balance = (int) Math.round(balance * (1 + interestRate));
        }

        lastInterestApplied = now;
    }
}
