package main.model;

public class Chequing extends Finance{

    public Chequing(int amountSpentToday, int dailyTransferLimit, int dailypurchaseLimit) {
        super(amountSpentToday, dailyTransferLimit, dailypurchaseLimit);
    }
}