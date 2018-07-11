package com.codecool.shop.process;

import com.codecool.shop.model.*;
import org.json.JSONObject;

public class PaymentProcess {

    public static boolean payment(Order order, JSONObject cardData) {

        if (cardData.getString("type").equals("cc")) {
            order.setCardData(new CreditCardPayment(cardData.getString("cardnumber"),
                                                    cardData.getString("holdername"),
                                                    Integer.parseInt(cardData.getString("cvcnumber")),
                                                    cardData.getString("expirydate")));
        }

        order.setStatus(Status.PAID);
        return true;
    }

}
