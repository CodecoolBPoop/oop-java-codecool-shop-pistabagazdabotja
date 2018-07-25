package com.codecool.shop.model;

public class Person {
    private String name;
    private String email;
    private String phone;
    private Address billingAddress;
    private Address shippingAddress;

    private CreditCard card;

    public Person(String name, String email, String phone, Address billingAddress, Address shippingAddress) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setCreditCard(CreditCard card) {
        this.card = card;
    }

    public CreditCard getCreditCard() {
        return card;
    }

    public String toString() {
        return getName() + " " + getEmail() + " " + getPhone() + " " + getShippingAddress() + " " + getBillingAddress() + " " + getCreditCard();
    }
}
