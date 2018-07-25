package com.codecool.shop.dao;

import com.codecool.shop.model.*;

import java.util.List;

public interface OrderDao {
    void add(LineItem product);
    void setUserData(String name, String email, String phone, Address billingAddress, Address shippingAddress);
    void printUserData();
    LineItem find(int id);
    void remove(int id);

    List<LineItem> getAll();
    List<LineItem> getBy(Supplier supplier);
    List<LineItem> getBy(ProductCategory productCategory);
}
