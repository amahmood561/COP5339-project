package com.project.cop5339.controller;


import com.project.cop5339.model.Seller;
import com.project.cop5339.service.SellerService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
@Controller
public class SellerController {

    private final SellerService sellerService;
    public Seller login(String username, String password) {
        return sellerService.authenticate(username, password);
    }
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    public Optional<Seller> getSellerById(Long sellerId) {
        return sellerService.getSellerById(sellerId);
    }

    public Seller createSeller( Seller seller) {
        return sellerService.createSeller(seller);
    }

    public Seller updateSeller( Long sellerId,  Seller seller) {
        return sellerService.updateSeller(sellerId, seller);
    }

    public void deleteSeller( Long sellerId) {
        sellerService.deleteSeller(sellerId);
    }

}
