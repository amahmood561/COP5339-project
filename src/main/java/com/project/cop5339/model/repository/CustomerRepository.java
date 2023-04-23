package com.project.cop5339.model.repository;


import com.project.cop5339.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    void deleteById(Long id);

    void delete(Customer customer);

}
