package com.codecool.shop.model;

import java.util.ArrayList;

public class OrderedProduct {

    private Product product;
    private int quantity;

    public OrderedProduct(Product product) {
        quantity = 1;
        this.product = product;
    }

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public int getProductId() {
        return product.getId();
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() { return product.getName(); }

    public String getPrice(){return product.getPrice();}

    public float getPriceAsFloat(){return product.getPriceAsFloat();}

    public int getId(){return product.getId();}

}
