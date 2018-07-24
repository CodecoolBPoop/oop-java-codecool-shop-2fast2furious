package com.codecool.shop.model;

import org.json.*;

public class Address {

    private String country;
    private String city;
    private int zipCode;
    private String address;

    public Address(String country, String city, String address, int zipCode) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    public static Address parseToAddress(JSONObject obj) {

        return new Address(obj.getString("country"),
                           obj.getString("city"),
                           obj.getString("address"),
                           Integer.parseInt(obj.getString("zip")));
    }

}
