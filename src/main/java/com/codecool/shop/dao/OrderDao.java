package com.codecool.shop.dao;

import com.codecool.shop.model.*;

import java.util.List;

public interface OrderDao {
    void orderCompleted();

    void add(LineItem product);
    void setUserData(String name, String email, String phone, Address billingAddress, Address shippingAddress);
    LineItem find(int id);
    void remove(int id);
    float getTotalPrice();

    List<LineItem> getAll();
    List<LineItem> getBy(Supplier supplier);
    List<LineItem> getBy(ProductCategory productCategory);

    void calculateTotalPrice();

    void addCreditCard(CreditCard card);
    CreditCard getCreditCard();
}
