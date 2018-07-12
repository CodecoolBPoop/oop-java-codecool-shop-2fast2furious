package com.codecool.shop.process;

import com.codecool.shop.model.*;
import org.json.JSONObject;

public class CheckoutProcess {

    public static boolean checkout(Order order, String name, String phoneNumber, String email, JSONObject saddress, JSONObject baddress) {
        User user = User.getInstance();
        user.setUserDataForCheckout(name, phoneNumber, email, Address.parseToAddress(saddress), Address.parseToAddress(baddress));
        order.setUser(user);
        order.setStatus(Status.CHECKED_OUT);
        return true;
    }

}
