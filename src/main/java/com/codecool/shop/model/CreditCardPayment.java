package com.codecool.shop.model;

public class CreditCardPayment extends Payment {

    private String cardNumber;
    private String holderName;
    private Integer cvcNumber;
    private String expiryDate;

    public CreditCardPayment(String cardNumber, String holderName, Integer cvcNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.cvcNumber = cvcNumber;
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() { return cardNumber; }

}
