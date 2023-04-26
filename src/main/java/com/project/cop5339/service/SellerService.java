package com.project.cop5339.service;

import com.project.cop5339.model.Seller;
import com.project.cop5339.model.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    /*public Seller getSalesReport(String sellerUsername) {
        Seller seller = sellerRepository.findByUsername(sellerUsername);
        if (seller != null) {
            new ResourceNotFoundException("Seller"+ "username"+sellerUsername);
        }

        List<Item> products = .findBySeller(seller);
        BigDecimal costs = BigDecimal.ZERO;
        BigDecimal revenues = BigDecimal.ZERO;
        for (Product product : products) {
            costs = costs.add(BigDecimal.valueOf(product.getInvoicePrice()* product.getQuantityAvailable()));
            revenues = revenues.add(BigDecimal.valueOf(product.getSellPrice() * product.getQuantityAvailable()));
        }

        BigDecimal profits = revenues.subtract(costs);
        seller.setCosts(costs);
        seller.setRevenues(revenues);
        seller.setProfits(profits);
        return seller;
    }*/

    public Seller authenticate(String username, String password) {
        Seller seller = sellerRepository.findByUsername(username);
        if (seller != null && seller.getPassword().equals(password)) {
            return seller;
        } else {
            return null;
        }
    }
    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }

    public Seller updateSeller(Long id, Seller seller) {
        Seller existingSeller = sellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found with id: " + id));
        existingSeller.setUsername(seller.getUsername());
        existingSeller.setPassword(seller.getPassword());
        return sellerRepository.save(existingSeller);
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Optional<Seller> getSellerById(Long sellerId) {
        return sellerRepository.findById(sellerId);
    }
}
