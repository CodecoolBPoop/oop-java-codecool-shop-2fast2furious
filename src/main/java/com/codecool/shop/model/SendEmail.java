package com.codecool.shop.model;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

    public static String productsToList(ArrayList<OrderedProduct> shoppingCart) {
        String result = "<table>\n" +
                "  <tr>\n" +
                "    <th>Number</th>\n" +
                "    <th>Product</th> \n" +
                "  </tr>";
        for (OrderedProduct product : shoppingCart) {
            result += "  <tr>\n" +
                      "    <td>" + product.getQuantity() + "</th>\n" +
                      "    <td>" + product.getName() + "</th> \n" +
                      "  </tr>";
        }
        result += "</table>";

        return result;
    }

    public static String buildBody(Order order) {
        return "<h3>Dear " + order.getUser().getName() + "!</h3><br>\n" +
                "<p>Your purchase is successfully administrated!</p>\n" +
                "<h4>Ordered items:</h4>\n" +
                productsToList(order.getShoppingCart()) + "\n" ;
    }

    public static void sendEmail(Order order) {

        String from = "2fast2furiouswebshop";
        String pass = "fastfurious";
        String[] to = { "brunnerm7@gmail.com" }; // list of recipient email addresses
        String subject = "Ride or die!";
        String body = buildBody(order);

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            message.setContent(buildBody(order), "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }

}
