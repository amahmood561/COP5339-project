package com.project.cop5339;

import com.project.cop5339.controller.CustomerController;
import com.project.cop5339.controller.SellerController;
import com.project.cop5339.model.Customer;
import com.project.cop5339.model.Seller;
import com.project.cop5339.swing.LoginScreen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        //SpringApplication.run(ProjectApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(ProjectApplication.class, args);

        SellerController sellerController = context.getBean(SellerController.class);
        //SellerScreen sellerScreen = new SellerScreen();
        //sellerScreen.setVisible(true);
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
        // Show the login screen
        LoginScreen loginScreen = new LoginScreen(sellerController, customerController);
        loginScreen.setVisible(true);



        // Launch the Swing application
        //HelloWorldController controller = context.getBean(HelloWorldController.class);
       //         HelloWorldSwing helloWorldSwing = new HelloWorldSwing(controller);
       // helloWorldSwing.showGUI();
    }

}
