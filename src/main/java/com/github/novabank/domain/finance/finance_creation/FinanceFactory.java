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

