package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface OrderDao {
    void add(LineItem product);
    LineItem find(int id);
    void remove(int id);

    List<LineItem> getAll();
    List<LineItem> getBy(Supplier supplier);
    List<LineItem> getBy(ProductCategory productCategory);
}
