package com.codecool.shop.controller;

import com.codecool.shop.model.Order;
import com.codecool.shop.process.PaymentProcess;
//import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/creditcard_payment"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cardNumber = req.getParameter("cardnumber");
        String holderName = req.getParameter("holdername");
        Integer cvcNumber = Integer.parseInt(req.getParameter("cvcnumber"));
        String expiryDate = req.getParameter("expirydate");

        HttpSession session = req.getSession();
        Object orderObj = session.getAttribute("orderController");
        Order orderController = (Order) orderObj;

        boolean result = PaymentProcess.payment(orderController, cardNumber, holderName, cvcNumber, expiryDate);
        System.out.println(orderController.getStatus());

    }

}
