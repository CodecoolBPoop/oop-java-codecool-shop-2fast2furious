package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /*ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier all = new Supplier("ALL", "fake supplier");
        supplierDataStore.add(all);
        Supplier mickey = new Supplier("Mickey Thompson", "Tires and wheels");
        supplierDataStore.add(mickey);
        Supplier hankook = new Supplier("Hankook", "Tires and wheels");
        supplierDataStore.add(hankook);
        Supplier yokohama = new Supplier("Yokohama", "Tires and wheels");
        supplierDataStore.add(yokohama);
        Supplier mazzi = new Supplier("Mazzi", "Best in wheels.");
        supplierDataStore.add(mazzi);
        Supplier dorman = new Supplier("Dorman", "Second in wheels.");
        supplierDataStore.add(dorman);
        Supplier euroActive = new Supplier("EuroActive", "");
        supplierDataStore.add(euroActive);

        //setting up a new product category
        productCategoryDataStore.add(new ProductCategory("ALL", "all", "fake category"));
        ProductCategory tire = new ProductCategory("Tire", "Tire", "Tires for cars");
        productCategoryDataStore.add(tire);
        ProductCategory wheel = new ProductCategory("Wheel", "Wheel", "Wheels for cars");
        productCategoryDataStore.add(wheel);


        //setting up products and printing it
        productDataStore.add(new Product("Mickey Thompson Baja MTZ All-Terrain Radial Tire", 250.9f, "USD", "Advanced radial construction for high mileage and smooth ride with PowerPly 3-ply sidewall on all sizes", tire, mickey));
        productDataStore.add(new Product("Hankook DynaPro AT-m RF10 Off-Road Tire", 100.9f, "USD", "Wraparound tread gives a rugged look and increases puncture resistance", tire, hankook));
        productDataStore.add(new Product("Yokohama Geolandar A/T-S On/Off-Road Tire", 64.9f, "USD", "All-Terrain tire developed for jeeps, pickup trucks, and SUVs", tire, yokohama));
        productDataStore.add(new Product("18\" Mazzi 342 HUSTLER Wheel Chrome", 137.9f, "USD", "WHEEL SPECS: 18\" diameter — 7.5\" wide — 5x108 5x114.3 bolt pattern — +40mm offset — 5.82\" backspace — 72.62mm center bore — 1600lb load rating", wheel, mazzi));
        productDataStore.add(new Product("18\" Mazzi 337 EDGE Wheel Black", 137.9f, "USD", "FITS: Many vehicles requiring a 5x112 or 5x120 bolt pattern. Use the vehicle compatibility tool on this listing to help verify fitment or contact our fitment specialists.", wheel, mazzi));
        productDataStore.add(new Product("Mazzi 365-8714B Galaxy Black Wheel with Milled Spokes", 134.9f, "USD", "Mazzi uses modern manufacturing technology to ensure the highest quality wheel", wheel, mazzi));
        productDataStore.add(new Product("Dorman 910-109 Ford Fusion 17 inch Wheel Cover Hub Cap", 57.9f, "USD", "Designed to match the original and blend in with the overall design of your vehicle", wheel, dorman));
        productDataStore.add(new Product("BMW Genuine Hamann HM EVO 8.5\" x 20\" Wheel Set", 5499.9f, "USD", "For all BMW Models", wheel, euroActive));
        productDataStore.add(new Product("Hankook DynaPro ATM RF10 Off-Road Tire", 117, "USD", "Wraparound tread gives a rugged look and increases puncture resistance", tire, hankook));
*/
        ProductDao productDataStore = ProductDaoSQL.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoSQL.getInstance();
        SupplierDao supplierDataStore = SupplierDaoSQL.getInstance();

        productCategoryDataStore.getAll();
        supplierDataStore.getAll();
        productDataStore.getAll();



        Order testOrder = new Order("Tomibacsi");
//        testOrder.TESTsaveOrderToDB();

        OrderDaoSQL daoSQL = OrderDaoSQL.getInstance();
        daoSQL.uploadOrder(testOrder);
    }
}
