package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.process.CheckoutProcess;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class Checkout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Order order = Order.getInstance();
        JSONObject userData = new JSONObject(req);


        boolean result = CheckoutProcess.Checkout(order, userData);


        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("true");

    }

}
