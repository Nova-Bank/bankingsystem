package com.github.novabank.domain.finance.finance_creation;

import com.github.novabank.domain.finance.finance_accounts.Chequing;
import com.github.novabank.domain.finance.finance_accounts.Credit;
import com.github.novabank.domain.finance.finance_accounts.Savings;

public class FinanceFactory {

    public static Chequing createChequing(String type) {
        switch (type.toLowerCase()) {
            case "premium":
                return new ChequingBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(3000000)
                        .setDailyWithdrawalLimit(999900)
                        .setDailyTransferLimit(2000000)
                        .build();

            case "base":
                return new ChequingBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(500000)
                        .setDailyWithdrawalLimit(300000)
                        .setDailyTransferLimit(300000)
                        .build();

            default:
                throw new IllegalStateException("Unknown chequing type: " + type);
        }
    }

    public static Savings createSavings(String type) {
        switch (type.toLowerCase()) {
            case "premium":
                return new SavingsBuilder()
                        .setInterestRate(0.03)
                        .build();

            case "base":
                return new SavingsBuilder()
                        .setInterestRate(0.02)
                        .build();

            default:
                throw new IllegalStateException("Unknown savings type: " + type);
        }
    }

    public static Credit createCredit(String type) {
        switch (type.toLowerCase()) {
            case "black":
                return new CreditBuilder().setCreditLimit(100000000)
                .build();
            case "red":
                return new CreditBuilder().setCreditLimit(10000000)
                .build();
            case "silver":
                return new CreditBuilder().setCreditLimit(1000000)
                .build();
            case "green":
                return new CreditBuilder().setCreditLimit(300000)
                .build();
            default:
                throw new IllegalStateException("Unknown credit type: " + type);
        }
    }
}
