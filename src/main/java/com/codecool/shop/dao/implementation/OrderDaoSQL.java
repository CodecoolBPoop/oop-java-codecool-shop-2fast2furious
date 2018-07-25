package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.Connector;
import com.codecool.shop.model.Order;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoSQL {

    private static OrderDaoSQL instance = null;
    private List<Order> retrievedOrders = new ArrayList<>();
    private Order singleOrder;

    private OrderDaoSQL() {
    }

    public static OrderDaoSQL getInstance() {
        if (instance == null) {
            instance = new OrderDaoSQL();
        }
        ;
        return instance;
    }

    public void upload(Order order) {

        //TODO Make iterable
        String orderUserName = ((order.getUserName()) == null) ? "" : order.getUserName();
        String username = (order.getUser() == null) ?  "" : order.getUser().getName();
        String status = order.getStatus().toString();
        String payment = (order.getPayment() == null) ? "" : order.getPayment().toString();
        String shippingCountry = (order.getShippingAddress() == null) ? "" : order.getShippingAddress().getCountry();
        String shippingCity = (order.getShippingAddress() == null) ? "" : order.getShippingAddress().getCity();
        String shippingAddress = (order.getShippingAddress() == null) ? "" : order.getShippingAddress().getAddress();
        String shippingPostcode = (order.getShippingAddress() == null) ? "" : String.valueOf(order.getShippingAddress().getZipCode());
        String billingCountry = (order.getBillingAddress() == null) ? "" : order.getBillingAddress().getCountry();
        String billingCity = (order.getBillingAddress() == null) ? "" : order.getBillingAddress().getCity();
        String billingAddress = (order.getBillingAddress() == null) ? "" : order.getBillingAddress().getAddress();
        String billingPostcode = (order.getBillingAddress() == null) ? "" : String.valueOf(order.getBillingAddress().getZipCode());

        java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());

        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("INSERT INTO orders (name, sh_country, sh_city, sh_address, sh_postcode, " +
                    "bill_country, bill_city, bill_address, bill_postcode, status, payment, time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);", statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, orderUserName);
            statement.setString(2, shippingCountry);
            statement.setString(3, shippingCity);
            statement.setString(4, shippingAddress);
            statement.setString(5, shippingPostcode);
            statement.setString(6, billingCountry);
            statement.setString(7, billingCity);
            statement.setString(8, billingAddress);
            statement.setString(9, billingPostcode);
            statement.setString(10, status);
            statement.setString(11, payment);
            statement.setTimestamp(12, sqlDate);


            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            int generatedKey = 0;
            if (resultSet.next()) {
                generatedKey = resultSet.getInt(1);
            }

            System.out.println("Inserted record's ID: " + generatedKey);
            System.out.println("DONE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
