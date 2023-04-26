package com.project.cop5339.service;


import com.project.cop5339.model.Item;
import com.project.cop5339.model.ShoppingCart;
import com.project.cop5339.model.repository.CustomerRepository;
import com.project.cop5339.model.repository.ItemRepository;
import com.project.cop5339.model.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class ShoppingCartService {
    private final CustomerRepository customerService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemRepository itemRepository;

    private final SoldItemsService soldItemsService;

    public ShoppingCartService(CustomerRepository customerService, ShoppingCartRepository shoppingCartRepository, ItemRepository itemRepository, SoldItemsService soldItemsService) {
        this.customerService = customerService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemRepository = itemRepository;
        this.soldItemsService = soldItemsService;
    }

    public Long createShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart).getCartId();
    }

    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShoppingCart"+ "id"+ id.toString()));
    }

    public ShoppingCart addItemToShoppingCart(ShoppingCart shoppingCart, Item item) {
        if (shoppingCart != null) {
            shoppingCart.getItems().add(item);
        }
        return  shoppingCart;
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
    public List<Item> getCartItems(Long id) {
        // Get the current shopping cart from the repository
        ShoppingCart shoppingCart = this.getShoppingCartById(id); // assuming cart ID is 1

        // If the cart is null, return an empty list
        if (shoppingCart == null) {
            return Collections.emptyList();
        }

        // Otherwise, return the items in the cart
        return shoppingCart.getItems();
    }
    public String checkout(ShoppingCart shoppingCart, long userId) {
        // Get the current shopping cart for the user

        // Check if the cart is empty
        if (shoppingCart.getItems().isEmpty()) {
            return "Your cart is empty. Please add items before checking out.";
        }

        // Calculate the total cost of the items in the cart
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Item item : shoppingCart.getItems()) {
            soldItemsService.saveSoldItems(item.getId(),userId,shoppingCart.getCartId());
            BigDecimal itemCost = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
            totalCost = totalCost.add(itemCost);
        }

        // Remove the items from the shopping cart
        shoppingCart.getItems().clear();

        return "Your order has been processed. Total cost: " + totalCost.toString();
    }
    public int getCartSize(Long id) {
        // Retrieve the current shopping cart from the repository
        ShoppingCart shoppingCart = this.getShoppingCartById(id);

        // If the shopping cart is not null, return the size of the list of items
        if (shoppingCart != null) {
            return shoppingCart.getItems().size();
        }

        // If the shopping cart is null, return 0
        return 0;
    }
}
