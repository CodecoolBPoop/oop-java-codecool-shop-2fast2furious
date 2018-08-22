package com.codecool.shop.model;

import com.codecool.shop.dao.Connector;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private ArrayList<OrderedProduct> shoppingCart = new ArrayList<OrderedProduct>();
    private ArrayList<Product> toSQL = new ArrayList<Product>();
    private User user;
    private Status status = Status.NEW;
    private Payment payment;
    private String userName;
    private Date date;
    private Address shippingAddress;
    private Address billingAddress;
    private Integer orderID = null;
    private int userID;

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Order() {
    }

    //FOR TESTING PURPOSES
    public Order(String username) {
        this.setUserName(username);
    }

    public void setOrder(User user, Status status, Payment payment, String userName, Address shippingAddress, Address billingAddress) {
        this.user = user;
        this.status = status;
        this.payment = payment;
        this.userName = userName;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addProduct(Product newProduct) {
        boolean isNew = true;
        for (OrderedProduct product : shoppingCart) {
            if (product.getProductId() == newProduct.getId()) {
                product.incrementQuantity();
                isNew = false;
                break;
            }
        }
        if (isNew) {
            shoppingCart.add(new OrderedProduct(newProduct));
        }
    }


    /*public static OrderController getInstance() {
        if (instance == null) {
            instance = new OrderController();
            instance.status = Status.NEW;
        }
        return instance;
    }*/

    public void removeProduct(Product oldProduct) {
        for (OrderedProduct product : shoppingCart) {
            if (product.getProductId() == oldProduct.getId()) {
                product.decrementQuantity();
                if (product.getQuantity() == 0) {
                    shoppingCart.remove(product);
                }
                break;
            }
        }
    }

    public ArrayList<OrderedProduct> getShoppingCart() {
        return shoppingCart;
    }

    public String toString() {
        String result = "---\nOrder #" + orderID + " \n";
        for (OrderedProduct product : shoppingCart) {
            result += "Product ID: " + product.getProductId() + ", Product name: " + product.getName() + ", Product quantity: " + product.getQuantity() + "\n";
        }
        result += "---";
        return result;

    }

    public float getTotal() {
        float total = 0;
        for (OrderedProduct prod : shoppingCart) {
            total += prod.getPriceAsFloat() * prod.getQuantity();
        }
        return total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCardData(Payment payment) {
        this.payment = payment;
    }

    public int getNumberOfOrdered() {
        int summa = 0;
        for (OrderedProduct pr : shoppingCart) {
            summa += pr.getQuantity();
        }
        return summa;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<Product> returnProductToSQL(){
        return toSQL;
    }

    public void emptyToSQL(){
        toSQL = new ArrayList<>();
    }

  /*  public void saveOrderToDB() {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("INSERT INTO orders (name, sh_country, sh_city, sh_postcode, sh_address," +
                    "bill_country, bill_city, bill_postcode, bill_address, status, payment) VALUES (?,?,?,?,?,?,?,?,?,?,?); " +
                    "SELECT currval('order_id_seq');", statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, this.getUserName());
            statement.setString(2, this.getShippingCountry());
            statement.setString(3, this.getShippingPostcode());
            statement.setString(4, this.getShippingPostcode());
            statement.setString(5, this.getShippingAddress());
            statement.setString(6, this.getBillingCountry());
            statement.setString(7, this.getBillingCity());
            statement.setString(8, this.getBillingPostcode());
            statement.setString(9, this.getShippingAddress());
            statement.setString(10, this.getStatus().toString());
            statement.setString(11, this.getPayment().toString());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            System.out.println(resultSet.first());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void TESTsaveOrderToDB() {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("INSERT INTO orders (name, sh_city) VALUES (?,?);", statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, this.getUserName());
            statement.setString(2, this.getShippingCity());
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
    */
}

