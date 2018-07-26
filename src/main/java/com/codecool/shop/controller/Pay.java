package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.CreditCard;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Pay", urlPatterns = ("/pay"))
public class Pay extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        orderDataStore.calculateTotalPrice();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        context.setVariable("totalPrice", orderDataStore.getTotalPrice());

        engine.process("product/pay.html", context, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        String card_number = request.getParameter("card-number");
        String card_holder = request.getParameter("card-holder");
        String expiration_date = request.getParameter("expiration-date");
        String cvv = request.getParameter("cvv");

        CreditCard card = new CreditCard(card_number, card_holder, expiration_date, cvv);
        orderDataStore.getPerson().setCreditCard(card);
        orderDataStore.orderCompleted();

        response.sendRedirect("/confirmation");
    }
}
