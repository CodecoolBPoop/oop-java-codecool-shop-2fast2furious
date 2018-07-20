package com.codecool.shop.controller;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@WebServlet(urlPatterns = {"/gmail-login"})
public class GmailLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idTokenString = req.getParameter("idToken");

        URL url = new URL("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + idTokenString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        String result = content.toString();
        JSONObject jsonObject = new JSONObject(result);
        String email = jsonObject.getString("email");
        System.out.println("YOUR EMAIL: " + email);

    }

}

