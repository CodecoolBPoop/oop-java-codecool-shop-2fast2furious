package com.codecool.shop.process;

import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Status;
import com.codecool.shop.model.User;
import org.json.JSONObject;

public class CheckoutProcess {

    public static boolean Checkout(Order order, String name, String phoneNumber, String email, JSONObject saddress, JSONObject baddress) {
        User user = User.getInstance();
        user.setUserDataForCheckout(name, phoneNumber, email, Address.parseToAddress(saddress), Address.parseToAddress(baddress));
        order.setUser(user);
        order.setStatus(Status.CHECKED_OUT);
        return true;
    }

}
