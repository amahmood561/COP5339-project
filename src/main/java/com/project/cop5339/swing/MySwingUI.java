package com.project.cop5339.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MySwingUI extends JFrame implements ActionListener {
    private final JButton myButton;

    public MySwingUI() {
        // Set up the main frame
        this.setTitle("My Swing UI");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a button and add it to the frame
        myButton = new JButton("Click me!");
        myButton.addActionListener(this);
        this.add(myButton);

        // Show the frame
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // When the button is clicked, call the controller
        if (e.getSource() == myButton) {
            // Call the controller method here
            // For example:
            // myController.doSomething();
        }
    }

    public static void main(String[] args) {
        // Create the Swing UI on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            new MySwingUI();
        });
    }
}
