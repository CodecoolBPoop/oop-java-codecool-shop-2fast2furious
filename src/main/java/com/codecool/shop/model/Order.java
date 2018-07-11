package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.ArrayList;

public class Order {

    private static Order instance = null;
    private ArrayList<OrderedProduct> shoppingCart = new ArrayList<OrderedProduct>();
    private User user;
    private Status status;
    private Payment payment;

    private Order() {
    }

    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
            instance.status = Status.NEW;
        }
        return instance;
    }


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
        String result = "";
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

}
