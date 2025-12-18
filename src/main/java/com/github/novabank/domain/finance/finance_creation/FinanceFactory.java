<<<<<<< HEAD
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
<<<<<<< HEAD
=======
package com.github.novabank.domain.finance;

/**
 * FinanceFactory is a DOMAIN layer utility for creating Finance objects.
 * * Runtime flow when registering a new account:
 *      Application Service -> Factory -> Builder -> Account Constructor -> Domain Object -> Repository -> DB
 * 
 * Instructions for the team:
 * - Use this class whenever you need to create a new Finance.
 * - Wrap Builder in Factory for validation
 * - Keeps object construction consistent and safe.
 * - Factory does NOT save to the database. Use AccountRepository.save(account) for that.
 * - Infrastructure does not need to call this. Only the domain/business logic should use the factory.
 *
 * Example usage:
 *   Account newAccount = AccountFactory.create("John Doe", initialBalance);
 */
// Note: Let me know if u want Lombok


public class FinanceFactory {
    public static Finance create(String type) {
        switch (type.toLowerCase()) {
            case "premium":
                new ChequingBuilder().setBalance(0).setDailyPurchaseLimit(3000000).setDailyWithdrawalLimit(999900).setDailyTransferLimit(2000000).build();
                new SavingsBuilder().setInterestRate(0.03);
            case "base":
                new ChequingBuilder().setBalance(0).setDailyPurchaseLimit(500000).setDailyWithdrawalLimit(300000).setDailyTransferLimit(300000).build();
                new SavingsBuilder().setInterestRate(0.03);
            default:
                throw new IllegalStateException("Unexpected value: " + type.toLowerCase());
        }
    }

    public static Finance Creditcreate(String type) {
        switch (type.toLowerCase()) {
            case "black":
                new CreditBuilder().setCreditLimit(100000000);
            case "red":
                new CreditBuilder().setCreditLimit(10000000);
            case "silver":
                new CreditBuilder().setCreditLimit(1000000);
            case "green":
                new CreditBuilder().setCreditLimit(300000);
            default:
                throw new IllegalStateException("Unexpected value: " + type.toLowerCase());
        }
    }
}

>>>>>>> origin/main
=======

>>>>>>> fbc9b96 ([feat] passed all test cases)
