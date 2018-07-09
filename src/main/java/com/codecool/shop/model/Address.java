package com.codecool.shop.model;

public class Address {

    private String country;
    private String city;
    private int zipCode;
    private String address;
    private String addressType;

    public Address(String country, String city, String address, String addressType, int zipCode) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.addressType = addressType;
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

    public String getAddressType() {
        return addressType;
    }

}
