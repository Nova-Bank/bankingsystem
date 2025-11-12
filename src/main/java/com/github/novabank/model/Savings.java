package com.github.novabank.model;

import java.time.YearMonth;
import java.time.ZoneId;

public class Savings extends Finance{
    private double interestRate = 0.05;
    private YearMonth lastSeen;

    public Savings(int amountSpentToday, int dailyTransferLimit, int dailypurchaseLimit) {
        super(amountSpentToday, dailyTransferLimit, dailypurchaseLimit);
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
