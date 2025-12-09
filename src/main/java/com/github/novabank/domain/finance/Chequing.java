package com.github.novabank.domain.finance;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.Year;


public class Chequing extends Finance{
    @Getter
    private double interestRate = 0.005;
    private Year lastSeen;

    public Chequing(int balance, int amountSpentToday, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit) {
        super(balance, amountSpentToday, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
        lastSeen = Year.now(ZoneId.of("America/Toronto"));
    }
    @Override
    public void interest() {
        Year now = Year.now(ZoneId.of("America/Toronto"));
        if(!now.equals(lastSeen)){
            balance = (int) Math.round(balance*(1+interestRate));
            lastSeen = now;
        }
    }

}
