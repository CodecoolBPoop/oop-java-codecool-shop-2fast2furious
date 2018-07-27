package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Status;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/remove_from_cart"})
public class RemoveFromCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter("id"));

        ProductDao productDataStore = ProductDaoSQL.getInstance();


        HttpSession session = req.getSession();
        Object orderObj = session.getAttribute("order");
        Order order = (Order) orderObj;

        if (!(session.getAttribute("email") == null || session.getAttribute("email").equals(""))){
            OrderDaoSQL.getInstance().uploadOrderWithCart(order);
        }


        if (order.getStatus() == Status.NEW) {
            Product product = productDataStore.find(productId);
            order.removeProduct(product);

            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(String.valueOf(order.getTotal()));
        }
    }

}
