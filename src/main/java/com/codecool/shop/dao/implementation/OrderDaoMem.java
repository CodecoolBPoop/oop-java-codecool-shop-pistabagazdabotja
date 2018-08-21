package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.*;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {
    private List<LineItem> data = new ArrayList<>();
    private static OrderDaoMem instance = null;

    private boolean isOrderCompleted = false;

    private Person person;
    private float totalPrice;

    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void orderCompleted() {
        isOrderCompleted = true;
    }

    @Override
    public boolean isOrderCompleted() {
        return isOrderCompleted;
    }

    @Override
    public int getNumberOfItems() {
        return data.size();
    }

    @Override
    public void emptyCart() {
        this.data.clear();
    }

    @Override
    public void add(LineItem lineitem) {

        // Check if exists
        int id = lineitem.getProduct().getId();
        for (LineItem li : data) {
            if (li.getProduct().getId() == id) return;
        }

        lineitem.setId(data.size() + 1);
        data.add(lineitem);
    }

    @Override
    public LineItem find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<LineItem> getAll() {
        return data;
    }
    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void calculateTotalPrice() {
        for (LineItem li : data) totalPrice += li.getAmount() * li.getProduct().getDefaultPrice();
    }

    @Override
    public float getTotalPrice() {
        return totalPrice;
    }
}
