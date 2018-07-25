package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.process.CheckoutProcess;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
//import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("phonenumber");
        String email = req.getParameter("email");
        JSONObject baddress = new JSONObject(req.getParameter("baddress"));
        JSONObject saddress = new JSONObject(req.getParameter("saddress"));

        HttpSession session = req.getSession();
        Object orderObj = session.getAttribute("order");
        Order order = (Order) orderObj;

        boolean result = CheckoutProcess.checkout(order, name, phoneNumber, email, baddress, saddress);

    }

}
