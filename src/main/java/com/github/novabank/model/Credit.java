package com.github.novabank.model;

public class Credit extends Finance{
    int creditBalance;
    int creditLimit;
    int creditInterestRate;

    public Credit(int creditBalance, int creditLimit, int creditInterestRate) {
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

    }

    public void purchase(int amount) {
    }

    public void closeBalance(int amount) {
        balance -= amount;
        creditBalance -= amount;
    }

}
