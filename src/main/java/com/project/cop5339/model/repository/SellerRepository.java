package com.project.cop5339.model.repository;


import com.project.cop5339.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Object> findByUsername(String sellerUsername);
}
