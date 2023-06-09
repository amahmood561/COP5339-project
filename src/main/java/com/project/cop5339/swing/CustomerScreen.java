package com.project.cop5339.swing;

import com.project.cop5339.controller.CustomerController;
import com.project.cop5339.controller.ItemsController;
import com.project.cop5339.controller.SellerController;
import com.project.cop5339.controller.ShoppingCartController;
import com.project.cop5339.model.Item;
import com.project.cop5339.model.ShoppingCart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerScreen extends JFrame {

    private ShoppingCartController shoppingCartController;

    private JLabel welcomeLabel;
    private JLabel cartLabel;
    private JButton viewCartButton;
    private JButton addItemButton;
    private JButton checkoutButton;
    private JButton removeItemButton;

    private JPanel panel;
    private GridBagConstraints constraints;

    public CustomerScreen(String username, long userId, SellerController sellerController, CustomerController customerController, ShoppingCartController shoppingCartController, ItemsController itemsController) {
        this.shoppingCartController = shoppingCartController;

        setTitle("Customer Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ShoppingCart cart = new ShoppingCart();
        long shoppingCartId = shoppingCartController.createCart();
        cart.setCartId(shoppingCartId);
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
                String message = shoppingCartController.viewCart(cart);

                // Display the items in a dialog box
                JOptionPane.showMessageDialog(CustomerScreen.this,
                        message,
                        "Your Cart",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });


        // create item save it as item then pass object to controller

        addItemButton = new JButton("Add Items");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<List<Item>, Void> worker = new SwingWorker<>() {
                    @Override
                    public List<Item> doInBackground() throws Exception {
                        return itemsController.getAllItems();
                    }

                    @Override
                    protected void done() {
                        try {
                            List<Item> items = get();

                            JCheckBox[] checkboxes = new JCheckBox[items.size()];
                            for (int i = 0; i < items.size(); i++) {
                                Item item = items.get(i);
                                checkboxes[i] = new JCheckBox(item.getName() + " - $" + item.getPrice());
                            }

                            int result = JOptionPane.showConfirmDialog(null, checkboxes, "Items", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            int count = 0;
                            if (result == JOptionPane.OK_OPTION) {
                                for (int i = 0; i < checkboxes.length; i++) {
                                    if (checkboxes[i].isSelected()) {
                                        count = count + 1;
                                        // Get the selected item from the list
                                        Item selectedItem = items.get(i);

                                        // Call the shopping cart controller to add the item to the cart
                                        shoppingCartController.addItemToCart(cart, selectedItem);
                                    }
                                }

                                // Update the cart label
                                cartLabel.setText("Cart: " + count);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                };
                worker.execute();
            }
        });
        removeItemButton = new JButton("Remove Items");
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the shopping cart controller to get the items in the cart
                List<Item> itemsInCart = cart.getItems();

                // Display the items in a dialog box with checkboxes
                JCheckBox[] checkboxes = new JCheckBox[itemsInCart.size()];
                for (int i = 0; i < itemsInCart.size(); i++) {
                    Item item = itemsInCart.get(i);
                    checkboxes[i] = new JCheckBox(item.getName() + " - $" + item.getPrice());
                }

                int result = JOptionPane.showConfirmDialog(null, checkboxes, "Items", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                int count = 0;
                if (result == JOptionPane.OK_OPTION) {
                    for (int i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].isSelected()) {
                            count = count + 1;
                            // Get the selected item from the list
                            Item selectedItem = itemsInCart.get(i);

                            // Call the shopping cart controller to remove the item from the cart
                            List<Item> items = cart.getItems();
                            items.remove(selectedItem);
                            cart.setItems(items);
                        }
                    }
                    List<Item> items = cart.getItems();
                    int size = items.size();
                    // Update the cart label
                    cartLabel.setText("Cart: " + size);
                }
            }
        });
        checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the shopping cart controller to process the checkout
                String message = shoppingCartController.checkout(cart, userId);

                // Display the checkout result in a dialog box
                JOptionPane.showMessageDialog(CustomerScreen.this,
                        message,
                        "Checkout Result",
                        JOptionPane.INFORMATION_MESSAGE);

                // Update the cart label
                cartLabel.setText("Cart: " + shoppingCartController.getCartSize(shoppingCartId));
            }
        });
        JButton signOutButton = new JButton("Sign Out");
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


        constraints.gridy = 5;
        panel.add(removeItemButton, constraints);

        // Add the sign-out button to the panel
        constraints.gridy = 6;
        panel.add(signOutButton, constraints);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
