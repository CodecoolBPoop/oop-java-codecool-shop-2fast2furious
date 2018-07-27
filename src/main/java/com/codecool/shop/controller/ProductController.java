package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
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
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String category = req.getParameter("category");
        if (category == null) {
            category = "ALL";
        }
        String supplier = req.getParameter("supplier");
        if (supplier == null) {
            supplier = "ALL";
        }

        HttpSession session = req.getSession(false);
        if (session == null) {
            session = req.getSession(true);
            session.setAttribute("order", new Order());
            session.setAttribute("email", "");
        }
        System.out.println("The email is " + session.getAttribute("email"));

        Object orderObj = session.getAttribute("order");
        Order order = (Order) orderObj;

        if (!session.getAttribute("email").equals("")) {
            Object email = session.getAttribute("email");
            order.setUserID(User.getIdByEmail((String)email));
        }

        ProductDao productDataStore = ProductDaoSQL.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoSQL.getInstance();
        SupplierDao supplierDataStore = SupplierDaoSQL.getInstance();


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("recipient", "World");
        context.setVariable("page", "product/main.html");
        context.setVariable("selectedCategory", category);
        context.setVariable("selectedSupplier", supplier);
        context.setVariable("sizeOfCart", order.getNumberOfOrdered());
        context.setVariable("allProdCat", productCategoryDataStore.getAll());
        context.setVariable("allSupp", supplierDataStore.getAll());
        context.setVariable("products", productDataStore.getBy(supplier, category));
        context.setVariable("session", session);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
