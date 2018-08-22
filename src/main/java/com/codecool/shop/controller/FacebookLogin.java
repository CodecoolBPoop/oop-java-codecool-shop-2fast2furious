package com.codecool.shop.controller;

import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/facebook-login"})
public class FacebookLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        HttpSession session = req.getSession(true);
        session = req.getSession(true);
        session.setAttribute("login_type", "facebook");
        session.setAttribute("email", email);
        User.registerUserWithAuth(email);

    }

}
