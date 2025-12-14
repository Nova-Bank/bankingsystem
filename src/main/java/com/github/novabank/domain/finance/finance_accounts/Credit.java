package com.github.novabank.domain.finance;

public class Credit extends Finance{
    int creditLimit;
    double creditInterestRate = 0.21;
    int maxuimimBalanceWithoutInterest;

    public Credit(int creditBalance, int creditLimit, double creditInterestRate, int balance, int amountSpentToday, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit, int dailySpendingLimit) {
        super(balance, amountSpentToday, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
        this.creditLimit = creditLimit;
        this.creditInterestRate = creditInterestRate;
        this.maxuimimBalanceWithoutInterest = (int) (creditLimit * 0.10);
    }

    /** add credit balance
     * Pyrchase -> if cost of purchase + credit balance > limit DENY transaction-> else
     * credit balance += Purchaseprice;
     * Accept purchase;
     *
     */
    @Override
    public void interest() {
        int lastMonth = -1;
        int m = java.time.LocalDate.now().getMonthValue();

        if (lastMonth == -1) {
            lastMonth = m;
            return;
        }

        if(m != lastMonth && balance > maxuimimBalanceWithoutInterest) {
            balance = (int) Math.round(balance*(1+creditInterestRate));
        }else{
            balance = 0;
        }
    }

    public void purchase(int amount) {
        if  (amount < 0) {
            throw new IllegalStateException("amount less than 0");
        }
        if (amount + balance > creditLimit) {
            throw new IllegalStateException("Credit limit exceeded");

        }
        balance += amount;
    }

    public void closeBalance(int amount) {
        balance -= amount;
    }

}
