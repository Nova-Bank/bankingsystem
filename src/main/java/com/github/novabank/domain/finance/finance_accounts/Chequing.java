<<<<<<< HEAD
package com.github.novabank.domain.finance.finance_accounts;

<<<<<<< HEAD
=======
package com.github.novabank.domain.finance;
import java.time.LocalDate;
import java.time.Month;
>>>>>>> origin/main
=======
import lombok.Getter;

import java.time.Clock;
>>>>>>> fbc9b96 ([feat] passed all test cases)
import java.time.YearMonth;
import java.time.ZoneId;

public class Chequing extends Finance {

    @Getter
    private final double interestRate = 0.005;

    private YearMonth lastInterestApplied;
    private final Clock clock;

    public Chequing(int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit, Clock clock) {
        super(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
        this.clock = clock;
        this.lastInterestApplied = null;
    }

<<<<<<< HEAD
    public Chequing(int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit) {
<<<<<<< HEAD
        super(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
=======
    public Chequing(int balance, int amountSpentToday, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit) {
        super(balance, amountSpentToday, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
>>>>>>> origin/main
        lastSeen = YearMonth.now(ZoneId.of("America/Toronto"));
=======
        this(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit,
                Clock.system(ZoneId.of("America/Toronto")));
>>>>>>> fbc9b96 ([feat] passed all test cases)
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
