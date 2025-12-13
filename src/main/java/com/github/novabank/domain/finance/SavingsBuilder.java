package com.github.novabank.domain.finance;

public class SavingsBuilder implements FinanceBuilder<Savings> {
    private int UID;
    private int balance;
    private int dailyWithdrawalLimit;
    private int dailyPurchaseLimit;
    private int dailyTransferLimit;
    private double interestRate;


//    public SavingsBuilder setBalance(int balance) {
//        this.balance = balance;
//        return this;
//    }
//    public SavingsBuilder setDailyWithdrawalLimit(int dailyWithdrawalLimit) {
//        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
//        return this;
//    }
//    public SavingsBuilder setDailyPurchaseLimit(int dailyPurchaseLimit) {
//        this.dailyPurchaseLimit = dailyPurchaseLimit;
//        return this;
//    }
//    public SavingsBuilder setDailyTransferLimit(int dailyTransferLimit) {
//        this.dailyTransferLimit = dailyTransferLimit;
//        return this;
//    }
//    public SavingsBuilder setUID(int UID) {
//        this.UID = UID;
//        return this;
//    }

    public SavingsBuilder setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }



    @Override
    public Savings build() {
        validate();
<<<<<<< HEAD
        return new Savings( dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit, UID);
=======
        return new Savings(UID,balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
>>>>>>> main
    }


    @Override
    public void validate() {
        if (balance < 0) {
            throw new IllegalStateException("balance less than 0");
        }
        if (dailyWithdrawalLimit <= 0) {
            throw new IllegalStateException("dailyWithdrawalLimit less than 0");
        }
        if (dailyPurchaseLimit <= 0) {
            throw new IllegalStateException("dailyPurchaseLimit less than 0");
        }
        if (dailyTransferLimit <= 0) {
            throw new IllegalStateException("dailyTransferLimit less than 0");
        }
        if (UID <= 0) {
            throw new IllegalStateException("UID less than 0");
        }
    }

    @Override
    public void reset() {
        this.UID = 0;
        this.balance = 0;
        this.dailyWithdrawalLimit = 0;
        this.dailyPurchaseLimit = 0;
        this.dailyTransferLimit = 0;
    }

    @Override
    public String toString() {
        return String.format("SavingsgBuilder[ID=%d balance=%d dailyWithdrawlLimit=%d, dailyPurchaseLimits=%d, dailyTransferLimit=%d]",
                UID, balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
    }
}
