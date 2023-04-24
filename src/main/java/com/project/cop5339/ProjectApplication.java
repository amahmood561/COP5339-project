package com.project.cop5339;

import com.project.cop5339.controller.SellerController;
import com.project.cop5339.swing.SellerScreen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        //SpringApplication.run(ProjectApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(ProjectApplication.class, args);
        // Launch the SellerScreen
        SellerController sellerController = context.getBean(SellerController.class);
        SellerScreen sellerScreen = new SellerScreen();
        sellerScreen.setVisible(true);
        // Launch the Swing application
        //HelloWorldController controller = context.getBean(HelloWorldController.class);
       //         HelloWorldSwing helloWorldSwing = new HelloWorldSwing(controller);
       // helloWorldSwing.showGUI();
    }

}
