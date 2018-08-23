package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.db.db_connection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.*;

public class ProductDaoJDBC implements ProductDao {
    private static ProductDaoJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJDBC() {
    }

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        String query = "INSERT INTO product (name, description, default_price, default_currency, supplier_id, category_id)" +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        LinkedList<Object> queryData = new LinkedList<>();
        queryData.add(product.getName());
        queryData.add(product.getDescription());
        queryData.add(product.getDefaultPrice());
        queryData.add(product.getDefaultCurrency());
        queryData.add(product.getSupplier().getId());
        queryData.add(product.getProductCategory().getId());

        db_connection.executeQuery(query, queryData);

    }

    @Override
    public Product find(int id) {
        String query = "SELECT id, name, description, default_price, default_currency, supplier_id, category_id FROM product " +
                       "WHERE id = " + id + ";";
        HashMap queryResult = db_connection.executeQueryWithResult(query).get(0);
        Product result =  new Product(queryResult);

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
    public List<Product> getAll() {
        String query = "SELECT id, name, description, default_price, default_currency, supplier_id, category_id " +
                       "FROM product;";

        List<Product> resultList = new ArrayList<>();
        List<HashMap> queryResult = db_connection.executeQueryWithResult(query);
        for (int i = 0; i < queryResult.size(); i++) {
            resultList.add(new Product(queryResult.get(i)));
        }

        return resultList;

    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT id, name, description, default_price, default_currency, supplier_id, category_id " +
                       "FROM product WHERE supplier_id = " + supplier.getId() + ";";

        List<Product> resultList = new ArrayList<>();
        List<HashMap> queryResult = db_connection.executeQueryWithResult(query);
        for (int i = 0; i < queryResult.size(); i++) {
            resultList.add(new Product(queryResult.get(i)));
        }

        return resultList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT id, name, description, default_price, default_currency, supplier_id, category_id " +
                       "FROM product WHERE product_category_id = " + productCategory.getId() + ";";

        List<Product> resultList = new ArrayList<>();
        List<HashMap> queryResult = db_connection.executeQueryWithResult(query);
        for (int i = 0; i < queryResult.size(); i++) {
            resultList.add(new Product(queryResult.get(i)));
        }

        return resultList;
    }
}
