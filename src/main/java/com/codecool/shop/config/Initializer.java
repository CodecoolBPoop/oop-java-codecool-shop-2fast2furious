package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoSQL.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoSQL.getInstance();
        SupplierDao supplierDataStore = SupplierDaoSQL.getInstance();
        OrderDaoSQL orderDaoSQL = OrderDaoSQL.getInstance();

        productCategoryDataStore.getAll();
        supplierDataStore.getAll();
        productDataStore.getAll();

    }
}
