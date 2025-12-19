package com.github.novabank.application.financal_actions;

import com.github.novabank.domain.account.accounts.Account;
import com.github.novabank.domain.account.AccountRepository;
import com.github.novabank.domain.finance.finance_accounts.CreditScoreCalculator;
import com.github.novabank.domain.finance.finance_accounts.CreditScoreRepository;
import com.github.novabank.domain.finance.finance_accounts.MonthlySnapshot;
import com.github.novabank.domain.finance.FinanceRepository;

import java.time.Clock;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class CreditScoreService {

    private final AccountRepository accountRepo;
    private final FinanceRepository financeRepo;
    private final CreditScoreRepository scoreRepo;
    private final CreditScoreCalculator calculator;
    private final Clock clock;

    public CreditScoreService(
            AccountRepository accountRepo,
            FinanceRepository financeRepo,
            CreditScoreRepository scoreRepo,
            CreditScoreCalculator calculator,
            Clock clock
    ) {
        this.accountRepo = accountRepo;
        this.financeRepo = financeRepo;
        this.scoreRepo = scoreRepo;
        this.calculator = calculator;
        this.clock = clock;
    }

    public void runMonthly() {
        YearMonth month = YearMonth.now(clock);

        for (Account a : accountRepo.findAll()) {
            int uid = a.getUID();

            if (scoreRepo.existsForMonth(uid, month)) {
                continue;
            }

            int netCents = financeRepo.getNetPositionCentsForAccount(uid);

            int ageMonths = (int) ChronoUnit.MONTHS.between(
                    a.getCreatedAt().withDayOfMonth(1),
                    LocalDate.now(clock).withDayOfMonth(1)
            );

            MonthlySnapshot snap = new MonthlySnapshot(uid, month, netCents, ageMonths);
            int score = calculator.calculate(snap);

            scoreRepo.save(uid, month, score, snap);
        }
    }
}
