package com.project.cop5339.model.repository;

import com.project.cop5339.model.Product;
import com.project.cop5339.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySellerId(Long sellerId);

    Optional<Object> findByIdAndSeller(Long productId, Seller seller);

    List<Product> findBySeller(Seller seller);
}
