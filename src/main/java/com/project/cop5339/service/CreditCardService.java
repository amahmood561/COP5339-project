package com.project.cop5339.service;

import com.project.cop5339.model.CreditCard;
import com.project.cop5339.model.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public Optional<CreditCard> getCreditCardByCardNumber(String cardNumber) {
        return creditCardRepository.findByCardNumber(cardNumber);
    }
}
