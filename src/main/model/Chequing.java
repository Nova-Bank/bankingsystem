package main.model;
import java.time.LocalDate;

public class Chequing extends Finance{

    public Chequing(int amountSpentToday, int dailyTransferLimit, int dailypurchaseLimit) {
        super(amountSpentToday, dailyTransferLimit, dailypurchaseLimit);
    }
    @Override
    public void interest() {
        if()
    }
}