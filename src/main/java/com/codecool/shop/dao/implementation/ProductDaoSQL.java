package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.Connector;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoSQL implements ProductDao {

    private List<Product> data = new ArrayList<>();
    private static ProductDaoSQL instance = null;
    private boolean queried = false;

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
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product>getAll() {
        if(queried){
            return data;
        }


        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SupplierDao suppliers = SupplierDaoSQL.getInstance();
        ProductCategoryDao categories = ProductCategoryDaoSQL.getInstance();

        try{
           connection = Connector.getConnection();

           statement = connection.prepareStatement("SELECT * FROM product");

           resultSet = statement.executeQuery();

           while (resultSet.next()){
               String name = resultSet.getString("name");
               float price = resultSet.getFloat("price");
               String description = resultSet.getString("description");
               String picture = resultSet.getString("picture");
               String currency = resultSet.getString("currency");
               int id = resultSet.getInt("id");
               int suppId = resultSet.getInt("supplier");
               Supplier supp = suppliers.find(suppId);
               int catId=resultSet.getInt("product_category");
               ProductCategory prodCat = categories.find(catId);
               data.add(createNewProduct(name,currency,description,price,supp,prodCat,id));
           }
           queried = true;


        }catch (SQLException e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Product> getBy(String supplier, String category) {
        List<Product> result = data.stream().collect(Collectors.toList());
        System.out.println(supplier + ", " + category);
        if (!supplier.equals("ALL")) {
            result = result.stream().filter(t -> t.getSupplier().getName().equals(supplier)).collect(Collectors.toList());
        }
        if (!category.equals("ALL")) {
            result = result.stream().filter(t -> t.getProductCategory().getName().equals(category)).collect(Collectors.toList());
        }
        System.out.println("Get by result: " + result);
        return result;

    }

    private Product createNewProduct(String name, String currency, String description, float price, Supplier supp, ProductCategory prodCat, int id){
        Product newProduct = new Product(name,price,currency,description,prodCat,supp);
        newProduct.setId(id);
        /*System.out.println(newProduct);*/
        return newProduct;
    }


}
