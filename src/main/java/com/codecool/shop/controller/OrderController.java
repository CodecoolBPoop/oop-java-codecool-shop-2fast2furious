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

@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        HttpSession session = req.getSession(false);
        if(session == null){
            session = req.getSession(true);
            session.setAttribute("email", "");
        }
        int id = Integer.parseInt((String)req.getParameter("id"));
        Order order = OrderDaoSQL.getInstance().retrieveOrderFromSQL(id);

        System.out.println("id: " + id + " user: " + order.getUserID());

        context.setVariable("page", "product/order.html");
        if (order.getUserID() == User.getIdByEmail(session.getAttribute("email").toString())) {
            context.setVariable("products", order.getShoppingCart());
            context.setVariable("total", order.getTotal());
        } else {
            context.setVariable("products", null);
            context.setVariable("total", 0);
        }
        engine.process("product/index.html", context, resp.getWriter());

    }



}
