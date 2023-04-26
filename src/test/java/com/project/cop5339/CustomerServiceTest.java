package com.project.cop5339;

import com.project.cop5339.model.Customer;
import com.project.cop5339.model.repository.CustomerRepository;
import com.project.cop5339.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void testSaveCustomer() {
        // Setup mock repository
        Customer customerToSave = Customer.createCustomer(1L, "johndoe", "password");
        Customer savedCustomer = Customer.createCustomer(1L, "johndoe", "password");
        when(customerRepository.save(customerToSave)).thenReturn(savedCustomer);

        // Call service method
        CustomerService customerService = new CustomerService(customerRepository);
        Customer result = customerService.saveCustomer(customerToSave);

        // Verify result
        assertEquals(savedCustomer, result);

        // Verify repository interaction
        verify(customerRepository, times(1)).save(customerToSave);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testGetAllCustomers() {
        // Setup mock repository
        List<Customer> customers = Arrays.asList(
                Customer.createCustomer(1L, "johndoe", "password"),
                Customer.createCustomer(2L, "janedoe", "password")
        );
        when(customerRepository.findAll()).thenReturn(customers);

        // Call service method
        CustomerService customerService = new CustomerService(customerRepository);
        List<Customer> result = customerService.getAllCustomers();

        // Verify result
        assertEquals(2, result.size());
        assertEquals("johndoe", result.get(0).getUsername());
        assertEquals("password", result.get(0).getPassword());
        assertEquals("janedoe", result.get(1).getUsername());
        assertEquals("password", result.get(1).getPassword());

        // Verify repository interaction
        verify(customerRepository, times(1)).findAll();
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testGetCustomerByUsername() {
        // Setup mock repository
        Customer customer = Customer.createCustomer(1L, "johndoe", "password");
        when(customerRepository.findByUsername("johndoe")).thenReturn(customer);

        // Call service method
        CustomerService customerService = new CustomerService(customerRepository);
        Customer result = customerService.getCustomerByUsername("johndoe");

        // Verify result
        assertEquals(customer, result);

        // Verify repository interaction
        verify(customerRepository, times(1)).findByUsername("johndoe");
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testAuthenticate() {
        // Setup mock repository
        Customer customer = Customer.createCustomer(1L, "johndoe", "password");
        when(customerRepository.findByUsername("johndoe")).thenReturn(customer);

        // Call service method with valid credentials
        CustomerService customerService = new CustomerService(customerRepository);
        Customer result = customerService.authenticate("johndoe", "password");

        // Verify result
        assertEquals(customer, result);

        // Call service method with invalid credentials
        result = customerService.authenticate("johndoe", "wrongpassword");

        // Verify result
        assertNull(result);

        // Verify repository interaction
        verify(customerRepository, times(2)).findByUsername("johndoe");
        verifyNoMoreInteractions(customerRepository);
    }

}
