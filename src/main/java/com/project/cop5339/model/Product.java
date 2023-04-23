package com.project.cop5339.model;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "invoice_price")
    private double invoicePrice;

    @Column(name = "sell_price")
    private double sellPrice;

    @Column(name = "quantity_available")
    private int quantityAvailable;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Product() {}

    public Product(String name, double invoicePrice, double sellPrice, int quantityAvailable) {
        this.name = name;
        this.invoicePrice = invoicePrice;
        this.sellPrice = sellPrice;
        this.quantityAvailable = quantityAvailable;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(double invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
