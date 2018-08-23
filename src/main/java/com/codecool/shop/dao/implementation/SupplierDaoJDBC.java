package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.db.db_connection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {
    private static SupplierDaoJDBC instance = null;

    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO supplier (name, description)" +
                       "VALUES (?, ?, ?)";

        LinkedList<Object> queryData = new LinkedList<>();
        queryData.add(supplier.getName());
        queryData.add(supplier.getDescription());

        db_connection.executeQuery(query, queryData);

    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT id, name, description, category_id FROM supplier " +
                       "WHERE id = " + id + ";";
        HashMap queryResult = db_connection.executeQueryWithResult(query).get(0);
        Supplier result =  new Supplier(queryResult);

        return result;
    }

    @Override
    public void remove(int id) {
        String query = "UPDATE supplier" +
                       "SET is_active = false" +
                       "WHERE id = " + id + ";";
        db_connection.executeQuery(query);

    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT id, name, supplier_id FROM supplier;";

        List<Supplier> resultList = new ArrayList<>();
        List<HashMap> queryResult = db_connection.executeQueryWithResult(query);
        for (int i = 0; i < queryResult.size(); i++) {
            resultList.add(new Supplier(queryResult.get(i)));
        }

        return resultList;
    }
}
