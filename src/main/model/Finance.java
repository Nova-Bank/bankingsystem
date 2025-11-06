package main.model;
public abstract class Finance {
    private balance;
    private total
    void withdraw(double balance, double amount){
        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        balance -= amount;
    }
    void deposit(double balance, double amount){
        if(amount < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        balance = balance - amount;
    }

}