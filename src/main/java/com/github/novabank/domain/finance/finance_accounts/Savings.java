package com.github.novabank.domain.finance.finance_accounts;

import lombok.Getter;


import java.time.ZoneId;
import java.time.Year;

public class Savings extends Finance{
    @Getter
    private double interestRate;
    private Year lastSeen;

    public Savings(int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit, double interestRate) {
        super(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
        this.interestRate = interestRate;
        lastSeen = Year.now(ZoneId.of("America/Toronto"));
    }


    @Override
    public void interest() {
        Year now = Year.now(ZoneId.of("America/Toronto"));
        if(!now.equals(lastSeen)){
            balance = (int) Math.round((balance)*(1+interestRate));
            lastSeen = now;
        }
    }
    

}
