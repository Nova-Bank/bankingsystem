package com.github.novabank.domain.finance.finance_creation;


import com.github.novabank.domain.finance.finance_accounts.Finance;

public class Credit extends Finance{
    int creditBalance;
    public int creditLimit;
    double creditInterestRate = 0.21;

    public Credit(int creditBalance, int creditLimit, double creditInterestRate, int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit, int dailySpendingLimit) {
        super(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
        this.creditBalance = creditBalance;
        this.creditLimit = 10000;
        this.creditInterestRate = creditInterestRate;
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

        if(m != lastMonth && creditBalance > 0) {
            creditBalance = (int) Math.round(creditBalance*(1+creditInterestRate));
        }else{
            creditBalance = 0;
        }
    }

    public void purchase(int amount) {
    }

    public void closeBalance(int amount) {
        balance -= amount;
        creditBalance -= amount;
    }

}
