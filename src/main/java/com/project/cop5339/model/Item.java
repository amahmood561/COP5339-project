package com.project.cop5339.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;
    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private BigDecimal price;
    @Column(nullable = false)
    private int quantity;

    @Column(nullable = true)
    private BigDecimal itemTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = true)
    private Long sellerId;
    public static Item createItem(Long id, String name, BigDecimal price, Long sellerId) {
        Item item = new Item();
        item.id = id;
        item.name = name;
        item.price = price;
        item.sellerId = sellerId;
        return item;
    }
    // getters and setters
    public Long getSellerId() {
        return sellerId;
    }
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public BigDecimal getPrice() {
        return this.price;
    }

    //public void setShoppingCart(ShoppingCart shoppingCart) {
     //   this.shoppingCart = shoppingCart;
   // }/

}
