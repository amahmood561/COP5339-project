package com.project.cop5339.swing;

import com.project.cop5339.controller.CustomerController;
import com.project.cop5339.controller.ItemsController;
import com.project.cop5339.controller.SellerController;
import com.project.cop5339.controller.ShoppingCartController;
import com.project.cop5339.model.Item;
import com.project.cop5339.model.Seller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class SellerScreen extends JFrame {

    private JPanel contentPane;
    private JTextField nameField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField productIdField;


    /**
     * Create the frame.
     */
    public SellerScreen(Long userId, SellerController sellerController, CustomerController customerController, ShoppingCartController shoppingCartController, ItemsController itemsController) {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        JPanel sellerPanel = new JPanel();
        tabbedPane.addTab("Seller Info", null, sellerPanel, null);
        sellerPanel.setLayout(new BorderLayout(0, 0));

        JPanel sellerFormPanel = new JPanel();
        sellerPanel.add(sellerFormPanel, BorderLayout.CENTER);
        sellerFormPanel.setLayout(new GridLayout(0, 2, 0, 5));

        JLabel nameLabel = new JLabel("Name:");
        sellerFormPanel.add(nameLabel);

        nameField = new JTextField();
        sellerFormPanel.add(nameField);
        nameField.setColumns(10);

        JLabel usernameLabel = new JLabel("Username:");
        sellerFormPanel.add(usernameLabel);

        usernameField = new JTextField();
        sellerFormPanel.add(usernameField);
        usernameField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password:");
        sellerFormPanel.add(passwordLabel);

        passwordField = new JTextField();
        sellerFormPanel.add(passwordField);
        passwordField.setColumns(10);

        JPanel sellerButtonPanel = new JPanel();
        sellerPanel.add(sellerButtonPanel, BorderLayout.SOUTH);

        JButton sellerSaveButton = new JButton("Save");
        sellerSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();

                Seller seller = new Seller();
                //seller.setName(name);
                seller.setUsername(username);
                seller.setPassword(password);
                sellerController.createSeller(seller);

                JOptionPane.showMessageDialog(null, "Seller saved successfully.");
            }
        });
        sellerButtonPanel.add(sellerSaveButton);

        JPanel productPanel = new JPanel();
        tabbedPane.addTab("Product Info", null, productPanel, null);
        productPanel.setLayout(new BorderLayout(0, 0));

        JPanel productFormPanel = new JPanel();
        productPanel.add(productFormPanel, BorderLayout.CENTER);
        productFormPanel.setLayout(new GridLayout(0, 2, 0, 5));

        JLabel productIdLabel = new JLabel("Product ID:");
        productFormPanel.add(productIdLabel);

        productIdField = new JTextField();
        productFormPanel.add(productIdField);
        productIdField.setColumns(10);


        // Create the price field
        JLabel productNameLabel = new JLabel("Product Name");
        productNameLabel.setBounds(50, 100, 100, 30);
        add(productNameLabel);
        JTextField productNameLabelField = new JTextField();
        productNameLabelField.setBounds(150, 100, 200, 30);
        add(productNameLabelField);


        // Create the price field
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 150, 100, 30);
        add(priceLabel);
        JTextField priceField = new JTextField();
        priceField.setBounds(150, 150, 200, 30);
        add(priceField);

        // Create the quantity field
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(50, 200, 100, 30);
        add(quantityLabel);
        JTextField quantityField = new JTextField();
        quantityField.setBounds(150, 200, 200, 30);
        add(quantityField);

        // Create the add product button
        JButton addProductButton = new JButton("Add Items");
        addProductButton.setBounds(50, 250, 300, 30);
        add(addProductButton);
        // fix below
        // Set up the action listener for the add product button
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = productNameLabelField.getText();
                BigDecimal price = new BigDecimal(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());


                Item item1 = new Item();
                item1.setName(productName);
                item1.setPrice(price);
                item1.setQuantity(quantity);
                item1.setSellerId(userId);
                Item created_item = itemsController.create(item1);


                // Display a message to indicate success
                JOptionPane.showMessageDialog(SellerScreen.this,
                        "Product \"" + productName + "\" added successfully with ID " +created_item.getId(),
                        "Product Added",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Create the view products button
        JButton viewProductsButton = new JButton("View Products");
        viewProductsButton.setBounds(50, 300, 300, 30);
        add(viewProductsButton);

        // Set up the action listener for the view products button
        viewProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the product service to get a list of products for the seller
                List<Item> items = itemsController.getAllItemsbySellerid(userId);

                // Display the products in a dialog box
                StringBuilder message = new StringBuilder();
                message.append("Your products:\n");
                for (Item item : items) {
                    message.append("\nProduct ID: ").append(item.getId())
                            .append("\nName: ").append(item.getName())
                            .append("\nPrice: ").append(item.getPrice())
                            .append("\nQuantity: ").append(item.getQuantity());
                }
                JOptionPane.showMessageDialog(SellerScreen.this,
                        message.toString(),
                        "Your Products",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(50, 350, 300, 30);
        add(signOutButton);
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of the current login screen
                dispose();
                // Create a new login screen
                LoginScreen loginScreen = new LoginScreen(sellerController, customerController, shoppingCartController, itemsController);
                loginScreen.setVisible(true);
            }
        });

        // Set up the screen
        setTitle("Seller Screen");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}