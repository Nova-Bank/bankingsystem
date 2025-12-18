package com.github.novabank.domain.finance.finance_accounts;

import com.github.novabank.domain.finance.finance_accounts.Credit;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class CreditScoreServiceTest {

    @Test
    void creditScore_updatesOncePerMonth_andAgainNextMonth() {
        ZoneId zone = ZoneId.of("UTC");

        Clock janClock = Clock.fixed(Instant.parse("2025-01-15T00:00:00Z"), zone);
        Clock febClock = Clock.fixed(Instant.parse("2025-02-15T00:00:00Z"), zone);

        Credit credit = newCredit(100_000, 0.21, 2_000, 5_000, 5_000, 5_000);

        TestAccount account = new TestAccount(
                123456,
                LocalDate.of(2024, 1, 1),
                13_000,
                credit
        );

        InMemoryCreditScoreRepository repo = new InMemoryCreditScoreRepository();
        CreditScoreService janService = new CreditScoreService(janClock, repo);

        int scoreJan1 = janService.updateMonthlyScore(account);
        int scoreJan2 = janService.updateMonthlyScore(account);

        assertEquals(scoreJan1, scoreJan2);

        CreditScoreService febService = new CreditScoreService(febClock, repo);

        int scoreFeb = febService.updateMonthlyScore(account);

        assertNotEquals(scoreJan1, scoreFeb);
        assertTrue(scoreJan1 >= 300 && scoreJan1 <= 850);
        assertTrue(scoreFeb >= 300 && scoreFeb <= 850);
    }

    @Test
    void creditScore_penalizesHighUtilization() {
        ZoneId zone = ZoneId.of("UTC");
        Clock clock = Clock.fixed(Instant.parse("2025-03-15T00:00:00Z"), zone);

        Credit lowUtil = newCredit(100_000, 0.21, 1_000, 5_000, 5_000, 5_000);
        Credit highUtil = newCredit(100_000, 0.21, 90_000, 5_000, 5_000, 5_000);

        TestAccount a = new TestAccount(1, LocalDate.of(2020, 1, 1), 20_000, lowUtil);
        TestAccount b = new TestAccount(2, LocalDate.of(2020, 1, 1), 20_000, highUtil);

        InMemoryCreditScoreRepository repo = new InMemoryCreditScoreRepository();
        CreditScoreService service = new CreditScoreService(clock, repo);

        int scoreA = service.updateMonthlyScore(a);
        int scoreB = service.updateMonthlyScore(b);

        assertTrue(scoreA > scoreB);
    }

    private static Credit newCredit(
            int creditLimit,
            double creditInterestRate,
            int balance,
            int dailyWithdrawalLimit,
            int dailyPurchaseLimit,
            int dailyTransferLimit
    ) {
        try {
            Constructor<Credit> c = Credit.class.getConstructor(
                    int.class, double.class, int.class, int.class, int.class, int.class
            );
            return c.newInstance(
                    creditLimit, creditInterestRate, balance,
                    dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit
            );
        } catch (ReflectiveOperationException ignored) {
        }

        try {
            Constructor<Credit> c = Credit.class.getConstructor(
                    int.class, double.class, int.class, int.class, int.class, int.class, int.class
            );
            int dailySpendingLimit = dailyPurchaseLimit;
            return c.newInstance(
                    creditLimit, creditInterestRate, balance,
                    dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit, dailySpendingLimit
            );
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(
                    "Your Credit constructor signature does not match expected forms. Fix Credit constructors first.",
                    e
            );
        }
    }

    static final class TestAccount {
        private final int uid;
        private final LocalDate createdOn;
        private final int monthEndNetWorthCents;
        private final Credit credit;

        TestAccount(int uid, LocalDate createdOn, int monthEndNetWorthCents, Credit credit) {
            this.uid = uid;
            this.createdOn = createdOn;
            this.monthEndNetWorthCents = monthEndNetWorthCents;
            this.credit = credit;
        }

        int uid() { return uid; }
        LocalDate createdOn() { return createdOn; }
        int monthEndNetWorthCents() { return monthEndNetWorthCents; }
        Credit credit() { return credit; }
    }

    static final class InMemoryCreditScoreRepository {
        private YearMonth lastScoredMonth;
        private Integer lastScore;

        YearMonth lastScoredMonth() { return lastScoredMonth; }
        Integer lastScore() { return lastScore; }

        void save(YearMonth month, int score) {
            this.lastScoredMonth = month;
            this.lastScore = score;
        }
    }

    static final class CreditScoreService {
        private final Clock clock;
        private final InMemoryCreditScoreRepository repo;

        CreditScoreService(Clock clock, InMemoryCreditScoreRepository repo) {
            this.clock = clock;
            this.repo = repo;
        }

        int updateMonthlyScore(TestAccount account) {
            YearMonth now = YearMonth.now(clock);

            if (now.equals(repo.lastScoredMonth()) && repo.lastScore() != null) {
                return repo.lastScore();
            }

            int score = calculateScore(account, now);
            repo.save(now, score);
            return score;
        }

        private int calculateScore(TestAccount account, YearMonth now) {
            int base = 300;

            int ageMonths = monthsBetween(YearMonth.from(account.createdOn()), now);
            int agePoints = clamp(ageMonths * 5, 0, 200);

            int netWorthPoints = clamp(account.monthEndNetWorthCents() / 1000, -100, 200);

            Credit credit = account.credit();
            double util = 0.0;
            if (credit.getCreditLimit() > 0) {
                util = (double) credit.getBalance() / (double) credit.getCreditLimit();
            }
            int utilPenalty = clamp((int) Math.round(util * 250.0), 0, 250);

            int raw = base + agePoints + netWorthPoints - utilPenalty;
            return clamp(raw, 300, 850);
        }

        private int monthsBetween(YearMonth start, YearMonth end) {
            int ys = end.getYear() - start.getYear();
            int ms = end.getMonthValue() - start.getMonthValue();
            return ys * 12 + ms;
        }

        private int clamp(int v, int lo, int hi) {
            if (v < lo) return lo;
            if (v > hi) return hi;
            return v;
        }
    }
}
