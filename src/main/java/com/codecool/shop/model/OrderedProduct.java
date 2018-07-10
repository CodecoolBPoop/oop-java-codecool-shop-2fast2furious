package com.codecool.shop.model;

import java.util.ArrayList;

public class OrderedProduct {

    private Product product;
    private int quantity;

    public OrderedProduct(Product product) {
        quantity = 1;
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

}
