package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoMem implements OrderDao {
    private boolean isOrderCompleted = false;

    private List<LineItem> data = new ArrayList<>();
    private static OrderDaoMem instance = null;

    private String name;
    private String email;
    private String phone;
    private Address billingAddress;
    private Address shippingAddress;

    private float totalPrice;

    private CreditCard card;

    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    public void orderCompleted() {
        isOrderCompleted = true;
    }

    public void setUserData(String name, String email, String phone, Address billingAddress, Address shippingAddress) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
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
    public List<LineItem> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<LineItem> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

    public void addCreditCard(CreditCard card) {
        this.card = card;
    }

    public CreditCard getCreditCard() {
        return card;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void calculateTotalPrice() {
        for (LineItem li: data) totalPrice += li.getAmount() * li.getProduct().getDefaultPrice();
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
}
