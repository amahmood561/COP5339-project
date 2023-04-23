package com.project.cop5339.model;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public Seller() {}

    public Seller(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
    this.sellerId = sellerId;
            }

    public String getUsername() {
            return username;
    }

    public void setUsername(String username) {
            this.username = username;
    }

    public String getPassword() {
            return password;
            }

    public void setPassword(String password) {
            this.password = password;
            }

    public void viewInventory() {
            // implementation for viewing inventory
            }

    public void addProduct() {
            // implementation for adding a product to inventory
            }

    public void updateInventory() {
            // implementation for updating inventory
            }

    public double getCosts() {
            // implementation for calculating costs
            return 0;
            }

    public double getRevenues() {
            // implementation for calculating revenues
            return 0;
            }

    public double getProfits() {
            // implementation for calculating profits
            return 0;
            }

    public void setCosts(BigDecimal costs) {
    }

    public void setRevenues(BigDecimal revenues) {
    }

    public void setProfits(BigDecimal profits) {
    }
}
