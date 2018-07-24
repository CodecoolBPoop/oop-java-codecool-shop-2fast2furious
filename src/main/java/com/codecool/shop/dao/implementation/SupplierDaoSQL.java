package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.Connector;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoSQL implements SupplierDao {

    List<Supplier> data = new ArrayList<>();
    boolean queried = false;
    static SupplierDaoSQL instance = null;

    public static SupplierDaoSQL getInstance(){
        if(instance == null){
            instance = new SupplierDaoSQL();
        }
        return instance;
    }



    private SupplierDaoSQL(){

    }


    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
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
    public List<Supplier> getAll() {

        if(queried){
            return data;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            connection = Connector.getConnection();

            statement = connection.prepareStatement("SELECT * FROM supplier");

            resultSet = statement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int id = resultSet.getInt("id");
                data.add(createNewSupplier(name,description, id));
            }

            queried = true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return data;
    }

    private Supplier createNewSupplier(String description, String name, int id){
        Supplier newSupplier = new Supplier(description, name);
        newSupplier.setId(id);
        return newSupplier;
    }
}
