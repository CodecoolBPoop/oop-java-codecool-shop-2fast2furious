package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Order;
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

@WebServlet(urlPatterns = {"/shopping_cart"})
public class ShoppingCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        HttpSession session = req.getSession(false);
        if(session == null){
            session = req.getSession(true);
            session.setAttribute("order", new Order());
        }

        Object orderObj = session.getAttribute("order");
        Order order = (Order)orderObj;

        context.setVariable("recipient", "World");
        context.setVariable("page", "product/shoppingcart.html");
        context.setVariable("products", order.getShoppingCart());
        context.setVariable("total", order.getTotal());
        engine.process("product/index.html", context, resp.getWriter());
    }

}
