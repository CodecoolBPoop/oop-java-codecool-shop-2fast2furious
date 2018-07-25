package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.ArrayList;

public class Order {


    private ArrayList<OrderedProduct> shoppingCart = new ArrayList<OrderedProduct>();
    private User user;
    private Status status = Status.NEW;
    private Payment payment;
    private int shoppingCartID;
    private String userName;
    private String shippingCountry;
    private String shippingCity;
    private String shippingPostcode;
    private String ShippingAddress;
    private String billingCountry;
    private String billingCity;
    private String billingPostcode;
    private String billingAddress;
    
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getShoppingCartID() {
        return shoppingCartID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }



    /*public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
            instance.status = Status.NEW;
        }
        return instance;
    }*/


    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        String result = "Im a fucking shopping cart ";
        for (OrderedProduct product : shoppingCart) {
            result += product.getProductId() + " " + product.getName() + " " + product.getQuantity() + "\n";
        }
        return result;

    }

    public float getTotal(){
        float total = 0;
        for(OrderedProduct prod: shoppingCart){
            total += prod.getPriceAsFloat() * prod.getQuantity();
        }
        return total;
    }

    public Status getStatus() {
        return status;
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

    public User getUser() { return user; }

    public Payment getPayment() { return this.payment; }




}
