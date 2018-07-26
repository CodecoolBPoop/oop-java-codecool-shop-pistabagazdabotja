package com.codecool.shop.dao;

import com.codecool.shop.model.*;

import java.util.List;

public interface OrderDao {
    void orderCompleted();
    boolean isOrderCompleted();

    int getNumberOfItems();
    void add(LineItem product);
    LineItem find(int id);
    void remove(int id);
    List<LineItem> getAll();

    void setPerson(Person person);
    Person getPerson();

    void calculateTotalPrice();
    float getTotalPrice();

}
