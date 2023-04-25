package com.project.cop5339.model;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;
    @Column(name = "costs")
    private BigDecimal costs;

    @Column(name = "revenues")
    private BigDecimal revenues;

    @Column(name = "profits")
    private BigDecimal profits;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Product> products;

    public Seller() {}

    public Seller(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getSellerId() {
        return id;
    }

    public void setSellerId(Long sellerId) {
        this.id = sellerId;
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

    public BigDecimal getCosts() {
        return costs;
    }

    public void setCosts(BigDecimal costs) {
        this.costs = costs;
    }

    public BigDecimal getRevenues() {
        return revenues;
    }

    public void setRevenues(BigDecimal revenues) {
        this.revenues = revenues;
    }

    public BigDecimal getProfits() {
        return profits;
    }

    public void setProfits(BigDecimal profits) {
        this.profits = profits;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
