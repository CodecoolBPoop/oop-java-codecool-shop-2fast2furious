package com.codecool.shop.model;


import com.codecool.shop.dao.Connector;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {


    private String name;
    private String phoneNumber;
    private String email;
    private Address shippingAddress;
    private Address billingAddress;




    public void setUserDataForCheckout(String name, String phoneNumber, String email, Address shippingAddress, Address billingAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public String getName() { return name; }

    public Address getShippingAddress() { return shippingAddress; }

    public Address getBillingAddress() { return billingAddress; }


    public static String getUsername(String email) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            statement = connection.prepareStatement("SELECT username FROM users WHERE email LIKE ?");
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("username");

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isPasswordNull(String email) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            statement = connection.prepareStatement("SELECT password FROM users WHERE email LIKE ?");
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getString("password") == null) {
                return true;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEmailUnique(String email){
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isUnique = true;

        try{
            statement = connection.prepareStatement("SELECT * FROM users WHERE email LIKE ?");
            statement.setString(1,email);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                String temp = resultSet.getString("email");
                if (temp.equals(email)){
                    isUnique = false;
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return isUnique;

    }

    public static boolean isUsernameUnique(String username){
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isUnique = true;

        try{
            statement = connection.prepareStatement("SELECT * FROM users WHERE username LIKE ?");
            statement.setString(1,username);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                String temp = resultSet.getString("username");
                if (temp.equals(username)){
                    isUnique = false;
                }
            }



        }catch (SQLException e){
            e.printStackTrace();
        }

        return isUnique;

    }

    public static void registerNewUser(String username, String password, String email){
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            statement = connection.prepareStatement("INSERT INTO users (password, username, email) VALUES (?,?,?)");
            statement.setString(1,password);
            statement.setString(2,username);
            statement.setString(3,email);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static void registerUserWithAuth(String email) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try{
            if (isEmailUnique(email)) {
                statement = connection.prepareStatement("INSERT INTO users (email) VALUES (?)");
                statement.setString(1,email);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static void insertPasswordToUser(String password, String email, String username) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try{
            if (isEmailUnique(password)) {
                statement = connection.prepareStatement("UPDATE users SET password = (?), username = (?) WHERE email LIKE (?)");
                statement.setString(1,password);
                statement.setString(2,username);
                statement.setString(3,email);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean validatePwAndEmail(String password, String email){
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            statement = connection.prepareStatement("SELECT * FROM users where email LIKE ?");
            statement.setString(1,email);
            resultSet = statement.executeQuery();
            try{
                resultSet.next();
                if(password.equals(resultSet.getString("password"))){
                    return true;
                }
            }catch (PSQLException e){
                return false;
            }


        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    public static int getIdByEmail(String email) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        System.out.println(email);

        try{
            statement = connection.prepareStatement("SELECT * FROM users where email LIKE ?");
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e){
            e.printStackTrace();
        }

        return 1;
    }
}
