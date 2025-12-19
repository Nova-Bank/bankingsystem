package com.github.novabank.domain.finance;

import com.github.novabank.domain.finance.finance_accounts.Finance;
import com.github.novabank.domain.finance.finance_accounts.Credit;

public class MinimumCreditPayment {

    private final int minFlatCents;
    private final double minPercent;

    public MinimumCreditPayment() {
        this.minFlatCents = 2500;
        this.minPercent = 0.02;
    }

    public MinimumCreditPayment(int minFlatCents, double minPercent) {
        this.minFlatCents = minFlatCents;
        this.minPercent = minPercent;
    }

    public int execute(Finance creditFinance) {
        if (!(creditFinance instanceof Credit)) {
            throw new IllegalStateException("MinimumCreditPayment requires a Credit account");
        }

        Credit credit = (Credit) creditFinance;

        int owed = credit.getBalance();
        if (owed <= 0) return 0;

        int percentPay = (int) Math.ceil(owed * minPercent);
        int minPay = Math.max(minFlatCents, percentPay);

        return Math.min(minPay, owed);
    }
}
