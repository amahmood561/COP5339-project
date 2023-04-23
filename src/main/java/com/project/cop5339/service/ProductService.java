package com.project.cop5339.service;

import com.project.cop5339.model.Product;
import com.project.cop5339.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId.toString()));
    }
    public List<Product> getProductsBySeller(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product productDetails) {
        Product product = getProductById(productId);
        product.setName(productDetails.getName());
        product.setInvoicePrice(productDetails.getInvoicePrice());
        product.setSellPrice(productDetails.getSellPrice());
        product.setQuantityAvailable(productDetails.getQuantityAvailable());
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.delete(getProductById(productId));
    }
}
