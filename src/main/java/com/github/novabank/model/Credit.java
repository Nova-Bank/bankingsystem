package com.github.novabank.model;

import java.time.ZoneId;

public class Credit extends Finance{
    int creditBalance;
    int creditLimit;
    double creditInterestRate = 0.21;

    public Credit(int creditBalance, int creditLimit, double creditInterestRate) {
        super(creditBalance, creditLimit, creditInterestRate);
        this.creditBalance = creditBalance;
        this.creditLimit = creditLimit;
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
