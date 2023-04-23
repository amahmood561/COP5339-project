package com.project.cop5339.model;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    public ShoppingCart() {}

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
        item.setShoppingCart(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setShoppingCart(null);
    }

    public void updateItem(Item item) {
        items.set(items.indexOf(item), item);
    }

    public double getCartTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getItemTotal().doubleValue();
        }
        return total;
    }

    public void checkout() {
        items.clear();
    }
}