package com.github.novabank.domain.finance.finance_creation;

public class FinanceFactory {

    public static ChequingBuilder createChequing(String type) {
        switch (type.toLowerCase()) {
            case "premium":
                return new ChequingBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(3000000)
                        .setDailyWithdrawalLimit(999900)
                        .setDailyTransferLimit(2000000);

            case "base":
                return new ChequingBuilder()
                        .setBalance(0)
                        .setDailyPurchaseLimit(500000)
                        .setDailyWithdrawalLimit(300000)
                        .setDailyTransferLimit(300000);

            default:
                throw new IllegalStateException("Unknown chequing type: " + type);
        }
    }

    public static SavingsBuilder createSavings(String type) {
        switch (type.toLowerCase()) {
            case "premium":
                return new SavingsBuilder()
                        .setInterestRate(0.03);

            case "base":
                return new SavingsBuilder()
                        .setInterestRate(0.02);

            default:
                throw new IllegalStateException("Unknown savings type: " + type);
        }
    }

    public static CreditBuilder createCredit(String type) {
        switch (type.toLowerCase()) {
            case "black":
                return new CreditBuilder().setCreditLimit(100000000);
            case "red":
                return new CreditBuilder().setCreditLimit(10000000);
            case "silver":
                return new CreditBuilder().setCreditLimit(1000000);
            case "green":
                return new CreditBuilder().setCreditLimit(300000);
            default:
                throw new IllegalStateException("Unknown credit type: " + type);
        }
    }
}
