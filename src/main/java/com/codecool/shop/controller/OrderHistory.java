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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/orderhistory"})
public class OrderHistory extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Order> orders = new ArrayList<Order>();

        orders = OrderDaoSQL.getInstance().retrieveAllOrdersByUserID(19);

        for(Order order:orders){
            System.out.println(order);
        }



    }



}
