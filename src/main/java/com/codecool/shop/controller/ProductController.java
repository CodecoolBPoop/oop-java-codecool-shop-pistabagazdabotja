package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.LineItem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        ProductDao productDataStore = ProductDaoJDBC.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJDBC.getInstance();
        SupplierDao supplierDataStore = SupplierDaoJDBC.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        context.setVariable("recipient", "World");
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        // SORT products
        String category = request.getParameter("category");
        String supplier = request.getParameter("supplier");

        if (category != null){
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(Integer.valueOf(category))));
            context.setVariable("category", productCategoryDataStore.find(Integer.parseInt(category)).getName());
            context.setVariable("supplier", "All suppliers");
        }
        else if (supplier != null) {
            context.setVariable("products", productDataStore.getBy(supplierDataStore.find(Integer.valueOf(supplier))));
            context.setVariable("supplier", supplierDataStore.find(Integer.parseInt(supplier)).getName());
            context.setVariable("category", "All categories");
        }
        else {
            context.setVariable("products", productDataStore.getAll());
            context.setVariable("category", "All categories");
            context.setVariable("supplier", "All suppliers");
        }

        context.setVariable("counter", Integer.toString(orderDataStore.getNumberOfItems()));

        engine.process("product/index.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        String id = request.getParameter("id");
        orderDataStore.add(new LineItem(productDataStore.find(Integer.valueOf(id))));

        int numOfProducts = orderDataStore.getNumberOfItems();

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(Integer.toString(numOfProducts));
    }

}
