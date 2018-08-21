package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoSQL;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoSQL;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/add_to_cart"})
public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter("id"));

        ProductDao productDataStore = ProductDaoSQL.getInstance();

        HttpSession session = req.getSession(false);
        if(session == null){
            session = req.getSession();
            session.setAttribute("order", new Order());
        }

        Object orderObj = session.getAttribute("order");
        Order order = (Order)orderObj;



        if (order.getStatus() == Status.NEW) {
            Product product = productDataStore.find(productId);
            order.addProduct(product);

            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(String.valueOf(order.getTotal()));
        }

        if (!(session.getAttribute("email") == null || session.getAttribute("email").equals(""))){
            OrderDaoSQL.getInstance().uploadOrderWithCart(order);
        }
    }

}
