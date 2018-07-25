package com.codecool.shop.controller;

<<<<<<< HEAD
import com.codecool.shop.model.Order;
=======
import com.codecool.shop.model.User;
>>>>>>> 6f0e1112a7ee2085e73bda03cd72398327e7a76d

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/default-login"})
public class DefaultLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String message;
        boolean success = User.validatePwAndEmail(password,email);

        if(!success){
            message = "Username or password invalid";
        }else{
            message = "success";
        }

        resp.getWriter().write(message);

    }

}
