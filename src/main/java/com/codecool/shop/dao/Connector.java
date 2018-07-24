package com.codecool.shop.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static String url;
    private static String dbname;
    private static String dbpw;
    private static boolean queried=false;

    public static Connection getConnection(){
        if(!queried){
            getConfig();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,dbname,dbpw);
        } catch (SQLException e){
            e.printStackTrace();
        }

        return connection;
    }


    private static void getConfig() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/main/resources/config.properties");

            prop.load(input);

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
