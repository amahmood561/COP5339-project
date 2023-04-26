package com.project.cop5339.service;

import com.project.cop5339.model.SoldItems;
import com.project.cop5339.model.repository.SoldItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoldItemsService {

    @Autowired
    private SoldItemsRepository soldItemsRepository;

    public SoldItems saveSoldItems(Long itemId, Long customerId, Long shoppingCartId) {
        SoldItems soldItems = new SoldItems(itemId, customerId, shoppingCartId);
        return soldItemsRepository.save(soldItems);
    }

    public SoldItems getSoldItemsById(Long id) {
        Optional<SoldItems> soldItems = soldItemsRepository.findById(id);
        return soldItems.orElse(null);
    }

    public List<SoldItems> getAllSoldItems() {
        return soldItemsRepository.findAll();
    }

    public void deleteSoldItemsById(Long id) {
        soldItemsRepository.deleteById(id);
    }
}
