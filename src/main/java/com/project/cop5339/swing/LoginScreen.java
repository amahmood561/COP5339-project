package com.project.cop5339.swing;

import com.project.cop5339.controller.CustomerController;
import com.project.cop5339.controller.ItemsController;
import com.project.cop5339.controller.SellerController;
import com.project.cop5339.controller.ShoppingCartController;
import com.project.cop5339.model.Customer;
import com.project.cop5339.model.Seller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginScreen extends JFrame {

    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;

    private JPanel panel;
    private GridBagConstraints constraints;

    private SellerController sellerController;
    private CustomerController customerController;

    public LoginScreen(SellerController sellerController, CustomerController customerController,  ShoppingCartController shoppingCartController, ItemsController itemsController) {
        this.sellerController = sellerController;
        this.customerController = customerController;

        setTitle("Login Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the username label and text field
        usernameLabel = new JLabel("Username:");
        usernameTextField = new JTextField(20);

        // Set up the password label and field
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        // Set up the login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked");

                // Get the entered username and password
                String username = usernameTextField.getText();
                String password = Arrays.toString(passwordField.getPassword());
                password = password.replaceAll("[^a-zA-Z0-9]", "");

                Customer customer = customerController.login(username, password);
                Seller seller = sellerController.login(username, password);

                // Determine if the user is a seller or customer and call the appropriate controller to log in
                if (seller != null) {
                    // Show the seller screen
                    dispose();
                    new SellerScreen(seller.getSellerId(), sellerController, customerController, shoppingCartController, itemsController);
                } else if (customer != null) {
                    // Show the customer screen
                    dispose();
                   new CustomerScreen(username,customer.getId(),sellerController, customerController, shoppingCartController, itemsController);
                } else {
                    // Show an error message
                    errorLabel.setText("Invalid username or password");
                }
            }
        });

        // Set up the error label
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);

        // Set up the panel and add the components
        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);
        constraints.gridx = 1;
        panel.add(usernameTextField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(loginButton, constraints);
        constraints.gridy = 3;
        panel.add(errorLabel, constraints);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}


