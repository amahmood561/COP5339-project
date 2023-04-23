package com.project.cop5339.controller;


import com.project.cop5339.model.Product;
import com.project.cop5339.service.ProductService;

import java.util.List;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public Product getProductById(Long productId) {
        return productService.getProductById(productId);
    }

    public Product createProduct(Product product) {
        return productService.createProduct(product);
    }

    public Product updateProduct(Long productId, Product product) {
        return productService.updateProduct(productId, product);
    }

    public void deleteProduct(Long productId) {
        productService.deleteProduct(productId);
    }

}

