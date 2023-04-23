package com.project.cop5339.service;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {


    public String getHelloWorldMessage() {
        return "Hello, World!";
    }
}