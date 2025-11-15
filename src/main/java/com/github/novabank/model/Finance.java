package com.github.novabank.model;
import java.time.LocalDate;

/**
 * Written By: Josef Geshelin
 * Toufic if you want to change the name of Finance to be better/more accurate do it ASAP as it'll be much harder later
 */

 /**
 * <-- -- [ADD DESCRIPTIONN ] -- -->
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
    protected int dailySpendingLimit;




    public Finance(int amountSpentToday, int dailyTransferLimit, int dailypurchaseLimit) {
        this.amountSpentToday = amountSpentToday;
        this.dailyTransferLimit = dailyTransferLimit;
        this.dailySpendingLimit = dailypurchaseLimit;
        this.dailyPurchaseLimit = dailyPurchaseLimit;
        this.balance = balance;
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    /* 
    Add Method overloading with floats, long etc...
    */ 
    /* 
     * Change setting 
    */
    void setAmountSpentToday(int amountSpentToday) {
        this.amountSpentToday = amountSpentToday;
    }
    void setDailyTransferLimit(int dailyTransferLimit) {
        //validate if user inputs negative value
        this.dailyTransferLimit = dailyTransferLimit;
    }
    void setDailySpendingLimit(int dailySpendingLimit) {
                //validate if user inputs negative value

        this.dailySpendingLimit = dailySpendingLimit;
    }
    void setBalance(int balance) {
        this.balance = balance;
    }
    void setDailyWithdrawalLimit(int dailyWithdrawalLimit) {
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }
    void setDailyPurchaseLimit(int dailyPurchaseLimit) {
        this.dailyPurchaseLimit = dailyPurchaseLimit;
    }
    public int getAmountSpentToday() {
        return amountSpentToday;
    }
    public int getDailyTransferLimit() {
        return dailyTransferLimit;
    }
    public int getDailySpendingLimit() {
        return dailySpendingLimit;
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

    public int withdraw(int amount, LocalDate date) {
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
        if (amount + dailyTransferLimit > balance) {
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
