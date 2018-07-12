package com.codecool.shop.model;


public class User {

    private static User instance = null;
    private String name;
    private String phoneNumber;
    private String email;
    private Address shippingAddress;
    private Address billingAddress;


    private User() {
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void setUserDataForCheckout(String name, String phoneNumber, String email, Address shippingAddress, Address billingAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public String getName() { return name; }


}
