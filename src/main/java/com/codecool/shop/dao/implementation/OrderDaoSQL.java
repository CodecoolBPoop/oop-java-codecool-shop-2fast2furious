package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.Connector;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderedProduct;
import com.codecool.shop.model.User;

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
        return instance;
    }

    public void uploadOrder(Order order) {

        //TODO Make iterable
        //TODO declare seperate method
        String orderUserName = ((order.getUserName()) == null) ? "" : order.getUserName();
        String username = (order.getUser() == null) ? "" : order.getUser().getName();
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
            System.out.println("Order saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void uploadOrderWithCart(Order order) {

        String orderUserName = ((order.getUserName()) == null) ? "" : order.getUserName();
        String username = (order.getUser() == null) ? "" : order.getUser().getName();
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
        int generatedKey = 0;

        String SQLmethod;


        try {
            if (order.getOrderID() != null) {
                statement = connection.prepareStatement("UPDATE orders " +
                        "SET name = ?," +
                        "sh_country = ?," +
                        "sh_city = ?," +
                        "sh_address = ?," +
                        "sh_postcode = ?," +
                        "bill_country = ?," +
                        "bill_city = ?," +
                        "bill_address = ?," +
                        "bill_postcode = ?," +
                        "status = ?," +
                        "payment = ? " +
                        "WHERE id = ?;");
            } else {
                statement = connection.prepareStatement("INSERT INTO orders (name, sh_country, sh_city," +
                        "sh_address, sh_postcode, bill_country, bill_city, bill_address, bill_postcode, status, payment, time, user_id)" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);", statement.RETURN_GENERATED_KEYS);
            }


            System.out.println(statement);
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
            if (order.getOrderID() == null) {
                statement.setTimestamp(12, sqlDate);
            } else {
                statement.setInt(12, order.getOrderID());
            }
            statement.setInt(13, order.getUserID());
            System.out.println(statement);
            statement.executeUpdate();

            if (order.getOrderID() == null) {
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    generatedKey = resultSet.getInt(1);
                    order.setOrderID(generatedKey);
                }
            }

            System.out.println("Inserted record's ID: " + generatedKey);
            System.out.println("Order saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (!order.getShoppingCart().isEmpty()) {
            statement = null;
            try {
                statement = connection.prepareStatement("DELETE FROM shopping_cart WHERE order_id = ?");
                statement.setInt(1, order.getOrderID());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            for (OrderedProduct ordered : order.getShoppingCart()) {
                try {
                    connection = Connector.getConnection();
                    statement = null;
                    resultSet = null;

                    statement = connection.prepareStatement("INSERT INTO shopping_cart (product, price, order_id) VALUES (?,?,?);");
                    statement.setInt(1, ordered.getProductId());
                    statement.setFloat(2, ordered.getPriceAsFloat());
                    statement.setInt(3, order.getOrderID());
                    statement.executeUpdate();
                    System.out.println("Cart saved");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Order retrieveOrderFromSQL(int orderId) {
        Order order = new Order();
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            if (!resultSet.getString("sh_country").equals("")) {
                Address saddress = new Address(resultSet.getString("sh_country"), resultSet.getString("sh_city"), resultSet.getString("sh_address"), resultSet.getInt("sh_postcode"));
                order.setShippingAddress(saddress);
            }
            if (!resultSet.getString("bill_country").equals("")) {
                Address baddress = new Address(resultSet.getString("bill_country"), resultSet.getString("bill_city"), resultSet.getString("bill_address"), resultSet.getInt("bill_postcode"));
                order.setBillingAddress(baddress);
            }

            int id = resultSet.getInt("id");
            order.setOrderID(id);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        statement = null;
        resultSet = null;
        ProductDao products = ProductDaoSQL.getInstance();

        try {
            statement = connection.prepareStatement("SELECT * FROM shopping_cart WHERE order_id = ?");
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                int productId = resultSet.getInt("product");
                order.addProduct(products.find(productId));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }


    public ArrayList retrieveAllOrdersByUserID(int id) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ArrayList<Order> ordersFromSQL = new ArrayList<>();

        try {
            statement = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ordersFromSQL.add(retrieveOrderFromSQL(resultSet.getInt("id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersFromSQL;
    }
}