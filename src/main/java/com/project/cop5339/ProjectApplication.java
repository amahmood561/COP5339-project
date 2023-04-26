package com.project.cop5339;

import com.project.cop5339.controller.CustomerController;
import com.project.cop5339.controller.ItemsController;
import com.project.cop5339.controller.SellerController;
import com.project.cop5339.controller.ShoppingCartController;
import com.project.cop5339.model.Customer;
import com.project.cop5339.model.Item;
import com.project.cop5339.model.Seller;
import com.project.cop5339.swing.LoginScreen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        System.setProperty("sun.java2d.d3d", "false");

        //SpringApplication.run(ProjectApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(ProjectApplication.class, args);
        ShoppingCartController shoppingCartController = context.getBean(ShoppingCartController.class);
        SellerController sellerController = context.getBean(SellerController.class);
        ItemsController itemsController = context.getBean(ItemsController.class);

        // Create a default seller and customer
        Seller seller = new Seller();
        seller.setUsername("seller");
        seller.setPassword("password");
        seller.setName("Default Seller");
        seller.setEmail("seller@example.com");
        sellerController.createSeller(seller);


        CustomerController customerController = context.getBean(CustomerController.class);
        // Create a default customer
        Customer customer = new Customer();
        customer.setUsername("customer");
        customer.setPassword("password");
        customer.setName("Default Customer");
        customer.setEmail("customer@example.com");
        customerController.createCustomer(customer);


        Item item1 = new Item();
        item1.setName("Item 1");
        item1.setPrice(new BigDecimal("10.99"));
        item1.setQuantity(5);
        item1.setSellerId(seller.getSellerId());

        itemsController.create(item1);

        Item item2 = new Item();
        item2.setName("Item 2");
        item2.setPrice(new BigDecimal("7.99"));
        item2.setQuantity(10);
        item2.setSellerId(seller.getSellerId());
        itemsController.create(item2);

        // Show the login screen
        LoginScreen loginScreen = new LoginScreen(sellerController, customerController, shoppingCartController, itemsController);
        loginScreen.setVisible(true);


    }

}
