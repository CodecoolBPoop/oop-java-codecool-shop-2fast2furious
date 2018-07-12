package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.*;
import com.codecool.shop.process.PaymentProcess;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/creditcard_payment"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject cardData = new JSONObject(req);
        Order order = Order.getInstance();

        boolean result = PaymentProcess.payment(order, cardData);

    }

}
