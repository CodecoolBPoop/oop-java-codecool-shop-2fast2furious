package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDaoSQL;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/orderhistory"})
public class OrderHistory extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Order> orders = new ArrayList<Order>();


        HttpSession session = req.getSession(false);
        String email="";
        try {
            email = (String)session.getAttribute("email");
        } catch (Exception e) {}

        ArrayList<Order> orders;
        OrderDaoSQL orderDao = OrderDaoSQL.getInstance();
        Integer id = User.getIdByEmail(email);
        System.out.println("id: " + id);
        orders = orderDao.retrieveAllOrdersByUserID(id);
        System.out.println("asdf");
        System.out.println(orders);
        System.out.println("ASDF");

        context.setVariable("page", "product/orderhistory.html");
        context.setVariable("session", session);
        context.setVariable("orders", orders);
        context.setVariable("orders", orders);
        engine.process("product/index.html", context, resp.getWriter());


    }



}
