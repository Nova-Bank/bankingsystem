package com.github.novabank.presentation.dtos;

public class TransferDTO {
    private String username;
    private String fromAccount;
    private String toAccount;
    private double amount;

    public TransferDTO() {}
    public TransferDTO(String username, String fromAccount, String toAccount, double amount) {
        this.username = username;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFromAccount() { return fromAccount; }
    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }
    public String getToAccount() { return toAccount; }
    public void setToAccount(String toAccount) { this.toAccount = toAccount; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
