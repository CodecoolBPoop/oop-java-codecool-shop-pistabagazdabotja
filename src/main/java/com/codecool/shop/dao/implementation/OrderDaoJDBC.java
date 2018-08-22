package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Person;

import java.util.List;

public class OrderDaoJDBC implements OrderDao {

    @Override
    public void orderCompleted() {

    }

    @Override
    public boolean isOrderCompleted() {
        return false;
    }

    @Override
    public void emptyCart() {

    }

    @Override
    public int getNumberOfItems() {
        return 0;
    }

    @Override
    public void add(LineItem product) {

    }

    @Override
    public LineItem find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<LineItem> getAll() {
        return null;
    }

    @Override
    public void setPerson(Person person) {

    }

    @Override
    public Person getPerson() {
        return null;
    }

    @Override
    public void calculateTotalPrice() {

    }

    @Override
    public float getTotalPrice() {
        return 0;
    }
}
