package com.project.cop5339.model;

import jakarta.persistence.*;

@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "exp_date")
    private String expDate;

    @Column(name = "cvv")
    private String cvv;

    public CreditCard() {
    }

    public CreditCard(String cardNumber, String expDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cvv = cvv;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
