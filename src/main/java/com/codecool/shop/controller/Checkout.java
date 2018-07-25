package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Address;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Checkout", urlPatterns = {"/checkout"})
public class Checkout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        engine.process("product/checkout.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        String bill_country = request.getParameter("bill-country");
        String bill_city = request.getParameter("bill-city");
        String bill_zip = request.getParameter("bill-zip");
        String bill_address = request.getParameter("bill-address");
        Address billingAddress = new Address(bill_country, bill_city, bill_zip, bill_address);

        String ship_country = request.getParameter("ship-country");
        String ship_city = request.getParameter("ship-city");
        String ship_zip = request.getParameter("ship-zip");
        String ship_address = request.getParameter("ship-address");
        Address shippingAddress = new Address(ship_country, ship_city, ship_zip, ship_address);

        orderDataStore.setUserData(name, email, phone, billingAddress, shippingAddress);

        response.sendRedirect("/pay");
    }
}
