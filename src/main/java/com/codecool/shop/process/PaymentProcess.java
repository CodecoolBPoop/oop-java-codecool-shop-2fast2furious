package com.codecool.shop.process;

import com.codecool.shop.model.*;
import org.json.JSONObject;

public class PaymentProcess {

    public static boolean payment(Order order, String cardNumber, String holderName, Integer cvcNumber, String expiryDate) {

        order.setCardData(new CreditCardPayment(cardNumber, holderName, cvcNumber, expiryDate));

        order.setStatus(Status.PAID);
        SendEmail.sendEmail(order);
        return true;
    }

}
