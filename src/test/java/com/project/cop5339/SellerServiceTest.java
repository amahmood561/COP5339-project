package com.project.cop5339;

import com.project.cop5339.model.Seller;
import com.project.cop5339.model.repository.SellerRepository;
import com.project.cop5339.service.SellerService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Seller testSeller = new Seller("newuser", "newpass");
        Seller savedSeller = sellerService.createSeller(testSeller);

        assertNotNull(savedSeller);

        Long sellerId = testSeller.getSellerId();
        Optional<Seller> foundSeller = sellerService.getSellerById(sellerId);
        assertNotNull(foundSeller);
        assertEquals(testSeller.getUsername(), foundSeller.get().getUsername());
    }



}
