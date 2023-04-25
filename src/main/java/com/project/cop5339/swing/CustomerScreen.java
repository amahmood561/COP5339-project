package com.project.cop5339.swing;

import com.project.cop5339.controller.ShoppingCartController;
import com.project.cop5339.model.ShoppingCart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerScreen extends JFrame {

    private ShoppingCartController shoppingCartController;

    private JLabel welcomeLabel;
    private JLabel cartLabel;
    private JButton viewCartButton;
    private JButton addItemButton;
    private JButton checkoutButton;

    private JPanel panel;
    private GridBagConstraints constraints;

    public CustomerScreen(ShoppingCartController shoppingCartController, String username) {
        this.shoppingCartController = shoppingCartController;

        setTitle("Customer Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ShoppingCart cart = new ShoppingCart();
        long shoppingCartId = shoppingCartController.createCart();
        // Set up the welcome label
        welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Set up the cart label
        cartLabel = new JLabel("Cart: " + shoppingCartController.getCartSize(shoppingCartId));
        cartLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        cartLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Set up the buttons
        viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the shopping cart controller to get the items in the cart
                String message = shoppingCartController.viewCart(shoppingCartId);

                // Display the items in a dialog box
                JOptionPane.showMessageDialog(CustomerScreen.this,
                        message,
                        "Your Cart",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addItemButton = new JButton("Add Item");
        // create item save it as item then pass object to controller
        /*addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter the item details
                JTextField nameField = new JTextField(10);
                JTextField priceField = new JTextField(10);
                JTextField quantityField = new JTextField(10);

                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Name:"));
                panel.add(nameField);
                panel.add(new JLabel("Price:"));
                panel.add(priceField);
                panel.add(new JLabel("Quantity:"));
                panel.add(quantityField);

                int result = JOptionPane.showConfirmDialog(CustomerScreen.this, panel, "Add Item",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    // Get the item details from the input fields
                    String name = nameField.getText();
                    BigDecimal price = new BigDecimal(priceField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());

                    // Call the shopping cart controller to add the item to the cart
                    shoppingCartController.addItemToCart(name, price, quantity);

                    // Update the cart label
                    cartLabel.setText("Cart: " + shoppingCartController.getCartSize(shoppingCartId));
                }
            }
        });*/

        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the shopping cart controller to process the checkout
                String message = shoppingCartController.checkout(shoppingCartId);

                // Display the checkout result in a dialog box
                JOptionPane.showMessageDialog(CustomerScreen.this,
                        message,
                        "Checkout Result",
                        JOptionPane.INFORMATION_MESSAGE);

                // Update the cart label
                cartLabel.setText("Cart: " + shoppingCartController.getCartSize(shoppingCartId));
            }
        });

        // Set up the panel and add the components
        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.gridx = 1;

        constraints.gridy = 1;
        panel.add(cartLabel, constraints);

        constraints.gridy = 2;
        panel.add(viewCartButton, constraints);

        constraints.gridy = 3;
        panel.add(addItemButton, constraints);

        constraints.gridy = 4;
        panel.add(checkoutButton, constraints);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
