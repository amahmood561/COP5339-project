package com.project.cop5339.swing;

import org.springframework.stereotype.Component;
// look at message model , message repo, helloworld controller and project application to see how its done
@Component
public class HelloWorldSwing {
    /*
    private HelloWorldController helloWorldController;
    public void showGUI() {
        createAndShowGUI();
    }

    @Autowired
    public HelloWorldSwing(HelloWorldController helloWorldController) {
        this.helloWorldController = helloWorldController;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create and set up the window
        JFrame frame = new JFrame("Hello World Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add a button to the frame
        JButton button = new JButton("Say Hello");
        button.addActionListener(e -> helloWorldController.sayHello());
        frame.getContentPane().add(button, BorderLayout.CENTER);

        // Display the window
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }*/
}
