<<<<<<< HEAD
package com.github.novabank.domain.finance.finance_accounts;

=======
package com.github.novabank.domain.finance;
import java.time.LocalDate;
import java.time.Month;
>>>>>>> origin/main
import java.time.YearMonth;
import java.time.ZoneId;


public class Chequing extends Finance{
    private double interestRate = 0.005;
    private YearMonth lastSeen;

<<<<<<< HEAD
    public Chequing(int balance, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit) {
        super(balance, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
=======
    public Chequing(int balance, int amountSpentToday, int dailyWithdrawalLimit, int dailyPurchaseLimit, int dailyTransferLimit) {
        super(balance, amountSpentToday, dailyWithdrawalLimit, dailyPurchaseLimit, dailyTransferLimit);
>>>>>>> origin/main
        lastSeen = YearMonth.now(ZoneId.of("America/Toronto"));
    }
    @Override
    public void interest() {
        YearMonth now = YearMonth.now(ZoneId.of("America/Toronto"));
        if(!now.equals(lastSeen)){
            balance = (int) Math.round(balance*(1+interestRate));
            lastSeen = now;
        }
    }

    public double getInterestRate(){
        return interestRate;
    }

}
