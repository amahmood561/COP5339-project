package com.project.cop5339.controller;

import com.project.cop5339.service.HelloWorldService;
import org.springframework.stereotype.Controller;

import javax.swing.*;

@Controller
public class HelloWorldController {
    private final HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public void sayHello() {
        String message = helloWorldService.getHelloWorldMessage();
        JOptionPane.showMessageDialog(null, message);
    }
}
