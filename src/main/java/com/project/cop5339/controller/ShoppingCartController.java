package com.project.cop5339.controller;

import com.project.cop5339.model.Item;
import com.project.cop5339.model.ShoppingCart;
import com.project.cop5339.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    public void addItemToCart(ShoppingCart cart, Item item) {

        shoppingCartService.addItemToShoppingCart(cart, item);
    }

    public Long createCart() {
        ShoppingCart cart = new ShoppingCart();
        return shoppingCartService.createShoppingCart(cart);
    }
    public String viewCart(ShoppingCart cart) {
        List<Item> items = cart.getItems();
        if (items.isEmpty()) {
            return "Your cart is empty.";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Item item : items) {
                sb.append("Item: ").append(item.getName()).append(", Quantity: ").append(item.getQuantity())
                        .append(", Price: ").append(item.getPrice()).append("\n");
            }
            return sb.toString();
        }
    }

    public String checkout(ShoppingCart shoppingCart) {
        return shoppingCartService.checkout(shoppingCart);
    }

    public int getCartSize(long Id) {
        return shoppingCartService.getCartSize(Id);
    }
}
