package com.codecool.shop.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/addtocart"})
public class AddToCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        System.out.println(id);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);
    }
}
