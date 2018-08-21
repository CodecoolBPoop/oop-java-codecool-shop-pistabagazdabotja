package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Person;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static java.lang.Integer.valueOf;

@WebServlet(name = "SendEmail", urlPatterns = {"/confirmation"})
public class SendEmail extends HttpServlet {
    public static void sendEmail() {
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        String from = "pistabagazdabotja";
        String pass = "pist@ba123";
        String[] to = {orderDataStore.getPerson().getEmail()}; // list of recipient email addresses
        String subject = "Confirmation - Pista Bácsi gazdaboltja";
        String body = createEmailBody();

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            message.setContent(body, "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    public static String createEmailBody() {
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        Person p = orderDataStore.getPerson();

        StringBuilder orderDetails = new StringBuilder();
        for (LineItem order : orderDataStore.getAll()) {
            orderDetails.append("<br/><br/>Product name: ");
            orderDetails.append(order.getProduct().getName());
            orderDetails.append("<br/>");
            orderDetails.append("Amount: ");
            orderDetails.append(order.getAmount());
            orderDetails.append("<br/>");
            orderDetails.append("Price: ");
            orderDetails.append(order.getProduct().getPrice());
            orderDetails.append("<br/>");
            orderDetails.append("Subtotal ");
            orderDetails.append(String.valueOf(valueOf(order.getAmount()) * order.getProduct().getDefaultPrice()) + " " + order.getProduct().getDefaultCurrency());
        }


        StringBuilder shippingDetails = new StringBuilder();
        shippingDetails.append(orderDataStore.getPerson().getShippingAddress().toString());

        String body = "<html>Dear " + p.getName() + "<br/><br/>" +
                "Your order has been processed." + "<br/><br/>" +
                "Details: " + orderDetails + "<br/><br/>" +
                "Total price: " +
                String.valueOf(orderDataStore.getTotalPrice()) +
                "<br/><br/>Shipping details: " +
                shippingDetails +
                "</html>";

        return body;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        sendEmail();

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        orderDataStore.emptyCart();


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());


        engine.process("product/confirmation.html", context, response.getWriter());

    }
}

