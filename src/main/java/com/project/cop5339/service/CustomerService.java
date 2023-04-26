package com.project.cop5339.service;

import com.project.cop5339.model.Customer;
import com.project.cop5339.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
    public Customer updateCustomer(Long customerId, Customer customerDetails) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer coundt not find by id"));

        customer.setUsername(customerDetails.getUsername());
        customer.setPassword(customerDetails.getPassword());
        // Update any other properties as needed

        Customer updatedCustomer = customerRepository.save(customer);
        return updatedCustomer;
    }
    public void deleteCustomerByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null) {
            customerRepository.delete(customer);
        }
    }
    public Customer authenticate(String username, String password) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        } else {
            return null;
        }
    }
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);

    }
}
