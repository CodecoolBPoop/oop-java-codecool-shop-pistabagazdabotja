package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoMem implements OrderDao {
    private List<LineItem> data = new ArrayList<>();
    private static OrderDaoMem instance = null;

    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
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
}
