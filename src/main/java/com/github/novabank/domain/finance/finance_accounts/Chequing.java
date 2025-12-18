package com.github.novabank.domain.finance.finance_accounts;

import lombok.Getter;

import java.time.Clock;
import java.time.YearMonth;
import java.time.ZoneId;

public class Chequing extends Finance {

    @Getter
    private final double interestRate = 0.005;

    private YearMonth lastInterestApplied;
    private final Clock clock;

    // Primary constructor (single source of truth)
    public Chequing(
            int balance,
            int dailyWithdrawalLimit,
            int dailyPurchaseLimit,
            int dailyTransferLimit,
            Clock clock
    ) {
        super(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
        this.clock = clock;
        this.lastInterestApplied = null;
    }

    // Convenience constructor (used by factory + tests)
    public Chequing(
            int balance,
            int dailyWithdrawalLimit,
            int dailyPurchaseLimit,
            int dailyTransferLimit
    ) {
        this(
                balance,
                dailyWithdrawalLimit,
                dailyPurchaseLimit,
                dailyTransferLimit,
                Clock.system(ZoneId.of("America/Toronto"))
        );
    }

    @Override
    public void interest() {
        YearMonth now = YearMonth.now(clock);

        if (lastInterestApplied != null && now.equals(lastInterestApplied)) {
            return; // already applied this month
        }

        if (balance > 0) {
            balance = (int) Math.round(balance * (1 + interestRate));
        }

        lastInterestApplied = now;
    }
}
