package com.project.cop5339.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sold_items")
public class SoldItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "itemId")
    private Long itemId;

    @Column(name = "customerId")
    private Long customerId;
    @Column(name = "shoppingCartId")
    private Long shoppingCartId;

    public SoldItems() {
    }

    public SoldItems(Long itemId, Long customerId, Long shoppingCartId) {
        this.itemId = itemId;
        this.customerId = customerId;
        this.shoppingCartId = shoppingCartId;

    }

    public Long getId() {
        return id;
    }
    public Long getshoppingCartId() {
        return shoppingCartId;
    }

    public Long getcustomerId() {
        return customerId;
    }

    public Long getitemId() {
        return itemId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setcustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setitemId(Long itemId) {
        this.itemId = itemId;
    }
    public void setshoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

}
