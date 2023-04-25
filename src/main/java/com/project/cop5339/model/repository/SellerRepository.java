package com.project.cop5339.model.repository;


import com.project.cop5339.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByUsername(String sellerUsername);
}
