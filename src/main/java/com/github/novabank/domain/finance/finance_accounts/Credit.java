package com.github.novabank.domain.finance.finance_accounts;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter 
@Setter
public class Credit extends Finance{
    private int lastMonth = -1;
     private int  creditLimit;
    private double creditInterestRate = 0.21;
    private @Getter @Setter int maximumBalanceWithoutInterest;

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
    // lastMonth should be a field, not local variable
    int currentMonth = LocalDate.now().getMonthValue();

    if (lastMonth == -1) {
        // first time interest is applied
        lastMonth = currentMonth;
        return;
    }

    if (currentMonth != lastMonth && balance > maximumBalanceWithoutInterest) {
        // apply interest only once per month
        balance = (int) Math.round(balance * (1 + creditInterestRate));
    }

    // update lastMonth to current month
    lastMonth = currentMonth;
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
    public int getMaximumBalanceWithoutInterest(){
        return maximumBalanceWithoutInterest;
    }
}
