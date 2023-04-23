package com.project.cop5339.service;


import com.project.cop5339.model.Item;
import com.project.cop5339.model.ShoppingCart;
import com.project.cop5339.model.repository.ItemRepository;
import com.project.cop5339.model.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemRepository itemRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ItemRepository itemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemRepository = itemRepository;
    }

    public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShoppingCart"+ "id"+ id.toString()));
    }

    public ShoppingCart addItemToShoppingCart(ShoppingCart shoppingCart, Item item) {
        item.setShoppingCart(shoppingCart);
        itemRepository.save(item);
        shoppingCart.getItems().add(item);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart removeItemFromShoppingCart(ShoppingCart shoppingCart, Item item) {
        shoppingCart.getItems().remove(item);
        itemRepository.delete(item);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart updateItemInShoppingCart(ShoppingCart shoppingCart, Item item, int quantity) {
        item.setQuantity(quantity);
        return shoppingCartRepository.save(shoppingCart);
    }

    public BigDecimal getShoppingCartTotal(ShoppingCart shoppingCart) {
        BigDecimal total = BigDecimal.ZERO;
        for (Item item : shoppingCart.getItems()) {
            total = total.add(item.getItemTotal());
        }
        return total;
    }
}
