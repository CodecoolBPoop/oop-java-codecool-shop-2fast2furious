package com.codecool.shop.process;

import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Status;
import com.codecool.shop.model.User;
import org.json.JSONObject;

public class CheckoutProcess {

    public static boolean Checkout(Order order, JSONObject userData) {
        User user = User.getInstance();
        user.setUserDataForCheckout(userData.getString("name"),
                                    userData.getString("phoneNumber"),
                                    userData.getString("email"),
                                    Address.parseToAddress(userData.getString("saddress")),
                                    Address.parseToAddress(userData.getString("baddress")));
        order.setUser(user);
        order.setStatus(Status.CHECKED_OUT);
        return true;
    }

}
