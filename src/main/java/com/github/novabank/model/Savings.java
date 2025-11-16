package com.github.novabank.model;

import java.time.YearMonth;
import java.time.ZoneId;

public class Savings extends Finance{
    private double interestRate = 0.05;
    private YearMonth lastSeen;

    public Savings(int balance, int amountSpentToday, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit, int dailySpendingLimit) {
        super(balance, amountSpentToday, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit, dailySpendingLimit);
    }
    @Override
    public void interest() {
        YearMonth now = YearMonth.now(ZoneId.of("America/Toronto"));
        if(!now.equals(lastSeen)){
            balance = (int) Math.round(balance*(1+interestRate));
            lastSeen = now;
        }
    }

}
