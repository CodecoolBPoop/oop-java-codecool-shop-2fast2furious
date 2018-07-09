package src.main.java.com.codecool.shop.model;

public class User {

    private String name;
    private String phoneNumber;
    private String email;
    private Address shippingAddress;
    private Address billingAddress;
    private int id;
    private static int count;

    public User() {
        count++;
        id = count;
    }

}
