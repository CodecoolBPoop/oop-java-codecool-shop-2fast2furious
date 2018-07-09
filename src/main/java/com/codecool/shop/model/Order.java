package src.main.java.com.codecool.shop.model;

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


}
