package com.codecool.shop.controller;

import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/signup"})
public class SignUp extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String message = "success";

        if(!User.isUsernameUnique(username)){
            message = "Username already in use";
        }else if(!User.isEmailUnique(email)){
            message = "Email already in use";
        }else{
            User.registerNewUser(username,password,email);
        }

        resp.getWriter().write(message);

    }

}
