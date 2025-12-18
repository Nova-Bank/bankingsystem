
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
                        .setDailyPurchaseLimit(3_000_000)
                        .setDailyWithdrawalLimit(999_900)
                        .setDailyTransferLimit(2_000_000)
                        .build();

            case "base":
                return new ChequingBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(500_000)
                        .setDailyWithdrawalLimit(300_000)
                        .setDailyTransferLimit(300_000)
                        .build();

            default:
                throw new IllegalStateException("Unknown chequing type: " + type.toLowerCase());
        }
    }

    public static Savings createSavings(String type) {
        switch (type.toLowerCase()) {
            case "premium":
                return new SavingsBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(3_000_000)
                        .setDailyWithdrawalLimit(999_900)
                        .setDailyTransferLimit(2_000_000)
                        .setInterestRate(0.03)
                        .build();

            case "base":
                return new SavingsBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(500_000)
                        .setDailyWithdrawalLimit(300_000)
                        .setDailyTransferLimit(300_000)
                        .setInterestRate(0.02)
                        .build();

            default:
                throw new IllegalStateException("Unknown savings type: " + type.toLowerCase());
        }
    }

    public static Credit createCredit(String type) {
        switch (type.toLowerCase()) {

            case "black":
                return new CreditBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(3_000_000)
                        .setDailyWithdrawalLimit(999_900)
                        .setDailyTransferLimit(2_000_000)
                        .setDailySpendingLimit(3_000_000)     // THIS was missing
                        .setCreditLimit(100_000_000)
                        .setCreditInterestRate(0.21)
                        .build();

            case "red":
                return new CreditBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(500_000)
                        .setDailyWithdrawalLimit(300_000)
                        .setDailyTransferLimit(300_000)
                        .setDailySpendingLimit(500_000)       // THIS was missing
                        .setCreditLimit(10_000_000)
                        .setCreditInterestRate(0.21)
                        .build();

            case "silver":
                return new CreditBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(300_000)
                        .setDailyWithdrawalLimit(200_000)
                        .setDailyTransferLimit(200_000)
                        .setDailySpendingLimit(300_000)       // THIS was missing
                        .setCreditLimit(1_000_000)
                        .setCreditInterestRate(0.21)
                        .build();

            case "green":
                return new CreditBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(100_000)
                        .setDailyWithdrawalLimit(100_000)
                        .setDailyTransferLimit(100_000)
                        .setDailySpendingLimit(100_000)       // THIS was missing
                        .setCreditLimit(300_000)
                        .setCreditInterestRate(0.21)
                        .build();

            default:
                throw new IllegalStateException("Unknown credit type: " + type.toLowerCase());
        }
    }
}
