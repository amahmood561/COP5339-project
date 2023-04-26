package com.project.cop5339;

import com.project.cop5339.model.Product;
import com.project.cop5339.model.Seller;
import com.project.cop5339.model.repository.ProductRepository;
import com.project.cop5339.model.repository.SellerRepository;
import com.project.cop5339.service.SellerService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SellerServiceTest {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before("")
    public void setUp() {
        // create test seller
        Seller testSeller = new Seller("testuser", "testpass");
        sellerRepository.save(testSeller);
    }

    @After("")
    public void tearDown() {
        // delete test seller
        Seller testSeller = (Seller) sellerRepository.findByUsername("testuser");
//                .orElse(null);
        if (testSeller != null) {
            sellerRepository.delete(testSeller);
        }
    }

    @Test
    public void testCreateSeller() {
        Seller newSeller = new Seller("newuser", "newpass");
        Seller savedSeller = sellerService.createSeller(newSeller);
        assertNotNull(savedSeller.getSellerId());
        assertEquals("newuser", savedSeller.getUsername());
        assertEquals("newpass", savedSeller.getPassword());
    }

    @Test
    public void testGetSellerById() {
        Seller testSeller = (Seller) sellerRepository.findByUsername("newuser");
//                .orElse(null);
        assertNotNull(testSeller);

        Long sellerId = testSeller.getSellerId();
        Optional<Seller> foundSeller = sellerService.getSellerById(sellerId);
        assertNotNull(foundSeller);
        assertEquals(testSeller.getUsername(), foundSeller.get().getUsername());
    }

    @Test
    public void testGetSalesReport() {
        Seller testSeller = (Seller) sellerRepository.findByUsername("newuser");
//                .orElse(null);
        assertNotNull(testSeller);

        Product product1 = new Product("product1",10.0, 20.0, 10);
        product1.setSeller(testSeller);
        Product product2 = new Product("product2", 15.0, 30.0, 5);
        product2.setSeller(testSeller);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        testSeller.setProducts(products);

        productRepository.save(product1);
        productRepository.save(product2);

        BigDecimal expectedCosts = BigDecimal.valueOf(175.0);
        BigDecimal expectedRevenues = BigDecimal.valueOf(350.0);
        BigDecimal expectedProfits = BigDecimal.valueOf(175.0);

        Seller salesReport = sellerService.getSalesReport("newuser");
        assertEquals(expectedCosts, salesReport.getCosts()); // will fail
        assertEquals(expectedRevenues, salesReport.getRevenues());
        assertEquals(expectedProfits, salesReport.getProfits());
    }

    // add more test methods as needed
}
