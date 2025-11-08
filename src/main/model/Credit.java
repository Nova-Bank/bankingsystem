package main.model;

public class Credit extends Finance{

    public Credit(int amountSpentToday, int dailyTransferLimit, int dailypurchaseLimit) {
        super(amountSpentToday, dailyTransferLimit, dailypurchaseLimit);
    }
}