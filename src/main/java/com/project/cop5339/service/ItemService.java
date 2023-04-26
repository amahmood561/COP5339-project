package com.project.cop5339.service;

import com.project.cop5339.model.Item;
import com.project.cop5339.model.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItemsBySellerId(Long sellerId) {
        return itemRepository.findBySellerId(sellerId);
    }
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.orElse(null);
    }
    public void setName(Long itemId, String name) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item not found with id " + itemId));
        item.setName(name);
        itemRepository.save(item);
    }

    public void setPrice(Long itemId, BigDecimal price) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item not found with id " + itemId));
        item.setPrice(price);
        itemRepository.save(item);
    }
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
