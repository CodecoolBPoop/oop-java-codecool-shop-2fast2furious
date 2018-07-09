package com.codecool.shop.model;

public class User {

    private String name;
    private String phoneNumber;
    private String email;
    private Address shippingAddress;
    private Address billingAddress;
    private int id;
    private static int count;

    public User(String name, String phoneNumber, String email, Address shippingAddress, Address billingAddress) {
        count++;
        id = count;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }



}
