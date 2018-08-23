package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.db.db_connection;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private static ProductCategoryDaoJDBC instance = null;

    private ProductCategoryDaoJDBC() {
    }

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO category VALUES (?, ?, ?);";
        LinkedList<Object> queryData = new LinkedList<>();
        queryData.add(category.getName());
        queryData.add(category.getDescription());
        queryData.add(category.getDepartment());

        db_connection.executeQuery(query, queryData);
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM category WHERE id = "+ id + ";";
        HashMap queryResult = db_connection.executeQueryWithResult(query).get(0);
        ProductCategory result = new ProductCategory(queryResult);

        return result;


    }

    @Override
    public void remove(int id) {
        String query = "UPDATE product" +
                "SET is_active = false" +
                "WHERE id = " + id + ";";

        db_connection.executeQuery(query);

    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM category;";

        List<ProductCategory> resultList = new ArrayList<>();
        List<HashMap> queryResult = db_connection.executeQueryWithResult(query);
        for (int i = 0; i < queryResult.size(); i++) {
            resultList.add(new ProductCategory(queryResult.get(i)));
        }

        return resultList;

    }
}
