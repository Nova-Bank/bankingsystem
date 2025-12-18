package com.github.novabank.presentation.dtos;

public class PaymentDTO {
    private String username;
    private String paymentFrom;
    private String paymentTo;
    private double amount;
    private String paymentType;

    public PaymentDTO() {}
    public PaymentDTO(String username, String paymentFrom, String paymentTo, double amount, String paymentType) {
        this.username = username;
        this.paymentFrom = paymentFrom;
        this.paymentTo = paymentTo;
        this.amount = amount;
        this.paymentType = paymentType;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPaymentFrom() { return paymentFrom; }
    public void setPaymentFrom(String paymentFrom) { this.paymentFrom = paymentFrom; }
    public String getPaymentTo() { return paymentTo; }
    public void setPaymentTo(String paymentTo) { this.paymentTo = paymentTo; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
}
