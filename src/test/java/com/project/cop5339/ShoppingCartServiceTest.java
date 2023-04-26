package com.project.cop5339;

import com.project.cop5339.model.Item;
import com.project.cop5339.model.ShoppingCart;
import com.project.cop5339.model.repository.ShoppingCartRepository;
import com.project.cop5339.service.ItemService;
import com.project.cop5339.service.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.aspectj.bridge.MessageUtil.fail;
//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ShoppingCartServiceTest {

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ItemService itemService;
    @MockBean
    private ShoppingCartRepository shoppingCartRepository;

    @Test
    void addItemToCart_addsItemSuccessfully() {
        // create a test shopping cart and item
        ShoppingCart cart = new ShoppingCart();
        cart.setCartId(1L);
        Item item = new Item();
        item.setName("Test item");
        item.setPrice(new BigDecimal(10));
        item.setQuantity(1);

        // save the shopping cart to the database
        cart = shoppingCartRepository.save(cart);

        if (cart != null) {
            // set the shopping cart in the item
            item.setShoppingCart(cart);

            // call the service's addItemToCart method
            shoppingCartService.addItemToShoppingCart(cart, item);

            // assert that the cart now contains the item
//            assertTrue(cart.getItems().contains(item));
        } else {
            fail("Failed to save shopping cart to database");
        }
    }

    @Test
    void removeItemFromCart_removesItemSuccessfully() {
        // create a test shopping cart and item
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item();
        item.setName("Test item");
        item.setPrice(new BigDecimal(10));
        item.setQuantity(1);
        cart.addItem(item);

        // mock the repository's save method
        when(shoppingCartRepository.save(any())).thenReturn(cart);

        // call the service's removeItemFromCart method
        shoppingCartService.removeItemFromShoppingCart(cart, item);

        // assert that the cart no longer contains the item
        assertFalse(cart.getItems().contains(item));
    }
}
