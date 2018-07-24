package com.codecool.shop.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Connector {
    private static String url;
    private static String dbname;
    private static String dbpw;
    private static boolean queried=false;

    public static Connection getConnection(){

    }


    private static void getConfig() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("resources/config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            url = prop.getProperty("url");
            dbname = prop.getProperty("dbname");
            dbpw = prop.getProperty("dbpw");
            queried = true;


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
