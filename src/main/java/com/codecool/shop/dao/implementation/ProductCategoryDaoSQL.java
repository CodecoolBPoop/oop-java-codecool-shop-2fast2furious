package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.Connector;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoSQL implements ProductCategoryDao {

    List<ProductCategory> data = new ArrayList<>();
    boolean queried = false;
    private static ProductCategoryDaoSQL instance = null;

    private ProductCategoryDaoSQL(){

    }

    public static ProductCategoryDaoSQL getInstance(){
        if(instance == null){
            instance = new ProductCategoryDaoSQL();
        }
        return instance;
    }


    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        if(!queried){
            getAll();
            queried = true;
        }
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        if(queried){
            return data;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = Connector.getConnection();

            statement = connection.prepareStatement("SELECT * FROM product_category");

            resultSet = statement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String department = resultSet.getString("department");
                int id = resultSet.getInt("id");
                data.add(createNewProductCategory(name, department,description, id));
            }

            queried = true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return data;
    }

    private ProductCategory createNewProductCategory(String name, String department, String description, int id){
        ProductCategory newProductCategory = new ProductCategory(name,department,description);
        newProductCategory.setId(id);
        return newProductCategory;
    }
}
