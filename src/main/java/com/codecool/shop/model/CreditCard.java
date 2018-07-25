package com.codecool.shop.model;

public class CreditCard {
    private String card_number;
    private String card_holder;
    private String expiration_date;
    private String cvv;

    public CreditCard(String card_number, String card_holder, String expiration_date, String cvv) {
        this.card_number = card_number;
        this.card_holder = card_holder;
        this.expiration_date = expiration_date;
        this.cvv = cvv;
    }

    public String getCard_number() {
        return card_number;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public String getCvv() {
        return cvv;
    }

    public String toString() {
        return card_holder + " " + card_number + " " + expiration_date + " (" + cvv + ")";
    }
}
