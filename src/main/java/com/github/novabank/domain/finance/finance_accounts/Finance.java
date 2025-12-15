package com.github.novabank.domain.finance.finance_accounts;
import java.time.LocalDate;

/**
 * 
 * @author Toufic Fares
 * @version 1.0
 * @since 2025-11-07
 */

 public abstract class Finance {

    // All values are in cents not dollar
    protected int balance;
    protected int amountSpentToday;
    protected int dailyWithdrawalLimit;
    protected int dailyPurchaseLimit;
    protected int dailyTransferLimit;

     public Finance(int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit) {
        this.balance = balance;
        this.amountSpentToday = 0;
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
        this.dailyPurchaseLimit = dailyPurchaseLimit;
        this.dailyTransferLimit = dailyTransferLimit;
    }

    void setAmountSpentToday(int amountSpentToday) {
        this.amountSpentToday = amountSpentToday;
        if (this.amountSpentToday < 0) {
            throw new IllegalArgumentException("amount spent today can't be negative");
        }
    }
    void setDailyTransferLimit(int dailyTransferLimit) {
        //validate if user inputs negative value
        this.dailyTransferLimit = dailyTransferLimit;
        if (this.dailyTransferLimit < 0) {
            throw new IllegalArgumentException("daily transfer limit can't be negative");
        }
    }
    

    void setBalance(int balance) {
        this.balance = balance;
        if (this.balance < 0) {
            throw new IllegalArgumentException("balance can't be negative");
        }
    }
    void setDailyWithdrawalLimit(int dailyWithdrawalLimit) {
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
        if (this.dailyWithdrawalLimit < 0) {
            throw new IllegalArgumentException("daily transfer limit can't be negative");
        }
    }
    void setDailyPurchaseLimit(int dailyPurchaseLimit) {
        this.dailyPurchaseLimit = dailyPurchaseLimit;
        if (this.dailyPurchaseLimit < 0) {
            throw new IllegalArgumentException("daily transfer limit can't be negative");
        }
    }

    public int getAmountSpentToday() {
        return amountSpentToday;
    }
    public int getDailyTransferLimit() {
        return dailyTransferLimit;
    }
    public int getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }
    public int getDailyPurchaseLimit() {
         return dailyPurchaseLimit;
     }

     public int getBalance() {
        return balance;
    }


    public int withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        if (amount + dailyWithdrawalLimit > balance) {
            throw new IllegalArgumentException("You have reached the daily withdrawl limit");
        }else{
            balance -= amount;
            amountSpentToday += amount;
        }
        return balance;
    }

    public int deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        balance += amount;
        return balance;
    }

    public boolean transfer(Finance sender,Finance reciever ,int amount, LocalDate date) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if(amount > sender.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        if (amount + dailyTransferLimit > balance)  {
            throw new IllegalArgumentException("You have reached the daily trasnfer limit");
        }else{
            sender.setBalance(sender.getBalance() - amount);
            reciever.setBalance(reciever.getBalance() + amount);
            return true;
        }

    }
    /* 
     * add abstract methods, 
    */
    public abstract void interest();

    // add toString()





}
