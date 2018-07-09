package com.codecool.shop.model;

import java.util.ArrayList;

public class Order {

    private ArrayList<OrderedProduct> shoppingCart = new ArrayList<OrderedProduct>();
    private User user;
    private Status status;
    private Payment paymentType;
    private int id;
    private static int count;

    public Order() {
        count++;
        id = count;
        status = Status.NEW;
    }

    public void setUser(User user) {
        this.user = user;
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


}
