package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoSQL implements ProductDao {


    private static ProductDaoSQL instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoSQL() {
    }

    public static ProductDaoSQL getInstance() {
        if (instance == null) {
            instance = new ProductDaoSQL();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product>getAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
           connection = getConnection();

           statement = connection.prepareStatement("SELECT * FROM product_category");


            System.out.println(statement);

            resultSet =statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString("name"));




        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Product> getBy(String supplier, String category) {
        return null;
    }

    private Connection getConnection() throws SQLException{
        String database = "jdbc:postgresql://31.46.27.4/2Fast";
        String dbUser = "rtamas";
        String dbPW = "gtasamp23";
        return DriverManager.getConnection(database,dbUser,dbPW);
    }
}
