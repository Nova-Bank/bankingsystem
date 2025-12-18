package com.github.novabank.domain.finance.finance_accounts;

public class SimpleCreditScoreCalculator implements CreditScoreCalculator {

    @Override
    public int calculate(MonthlySnapshot s) {
        int base = 300;

        int agePoints = Math.min(200, s.accountAgeMonths() * 5);

        int dollars = Math.max(0, s.netPositionCents() / 100);
        int positionPoints = Math.min(350, dollars / 50);

        int score = base + agePoints + positionPoints;
        if (score < 300) return 300;
        if (score > 850) return 850;
        return score;
    }
}
