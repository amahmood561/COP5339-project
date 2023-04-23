package com.project.cop5339.controller;

import com.project.cop5339.model.Customer;
import com.project.cop5339.service.CustomerService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerService.getCustomerByUsername(String.valueOf(id));
    }

    public Customer createCustomer( Customer customer) {
        return customerService.createCustomer(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        return customerService.saveCustomer(customer);
    }

    public void deleteCustomerByUsername(String username) {
        customerService.deleteCustomerByUsername(username);
    }
}
