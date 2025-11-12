package com.github.novabank.model;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;


public class Chequing extends Finance{
    private double interestRate = 0.0005;
    private YearMonth lastSeen;

    public Chequing(int amountSpentToday, int dailyTransferLimit, int dailypurchaseLimit) {
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
