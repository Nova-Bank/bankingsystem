package com.github.novabank.domain.finance.finance_accounts;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class Credit extends Finance{

     private int  creditLimit;
    private double creditInterestRate = 0.21;
    private @Getter int maximumBalanceWithoutInterest;

    public Credit(int creditLimit, double creditInterestRate, int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit, int dailySpendingLimit) {
        super(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
        this.creditLimit = creditLimit;
        this.creditInterestRate = creditInterestRate;
        this.maximumBalanceWithoutInterest = (int) (creditLimit * 0.10);
    }

    /** add credit balance
     * Pyrchase -> if cost of purchase + credit balance > limit DENY transaction-> else
     * credit balance += Purchaseprice;
     * Accept purchase;
     *
     */
    @Override
    public void interest() {
        int lastMonth = -1; //TODO change this as everytime its called lastMonth resets to -1
        int m = java.time.LocalDate.now().getMonthValue();

        if (lastMonth == -1) {
            lastMonth = m;
            return;
        }

        if(m != lastMonth && balance > maximumBalanceWithoutInterest) {
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
