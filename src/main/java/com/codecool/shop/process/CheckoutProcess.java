package com.codecool.shop.process;

import com.codecool.shop.dao.implementation.OrderDaoSQL;
import com.codecool.shop.model.*;
import org.json.JSONObject;

public class CheckoutProcess {

    public static boolean checkout(Order order, String name, String phoneNumber, String email, JSONObject saddress, JSONObject baddress) {

       order.setStatus(Status.CHECKED_OUT);
       order.setShippingAddress(Address.parseToAddress(saddress));
       order.setBillingAddress(Address.parseToAddress(baddress));
       OrderDaoSQL.getInstance().uploadOrderWithCart(order);
       return true;
    }

}
