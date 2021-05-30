package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;

import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static com.example.demo.TestUtils.injectObjects;
import static  org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/** Rubric - Write tests and meet an acceptable code coverage level.
 * Write proper tests for the above module and meet an acceptable code coverage level.
 * Code coverage of 60% is met through tests. The tests include sanity
 * and regression test cases and take care of negative tests as well.
 * */

public class CartControllerTest {

    private CartController cartController;

    private UserRepository userRepo = mock(UserRepository.class);

    private CartRepository cartRepo = mock(CartRepository.class);

    private ItemRepository itemRepo = mock(ItemRepository.class);

    @Before
    public void setUp(){

        cartController = new CartController();
        injectObjects(cartController, "userRepository", userRepo);
        injectObjects(cartController, "cartRepository", cartRepo);
        injectObjects(cartController,"itemRepository", itemRepo);

        Item item = new Item();
        item.setId(1L);
        item.setName("Test Item");
        BigDecimal price = BigDecimal.valueOf(1.95);
        item.setPrice(price);
        item.setDescription("Lorum Epsom");
        when(itemRepo.findById(1L)).thenReturn(java.util.Optional.of(item));

        /** Udacity Review Feedback to mock the userRepo
         * same way as mocked itemRepo, as addToCard method was trying
         * to locate user at first
         * */
        User user1 = new User();
        Cart cartMock = new Cart();
        user1.setId(0);
        user1.setUsername("test");
        user1.setPassword("testPassword");
        user1.setCart(cartMock);
        when(userRepo.findByUsername("test")).thenReturn(user1);

    }

    @Test
    public void addTocart(){
        ModifyCartRequest r = new ModifyCartRequest();
        r.setItemId(1L);
        r.setQuantity(1);
        r.setUsername("test");
        ResponseEntity<Cart> response = cartController.addTocart(r);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        Cart c = response.getBody();
        assertNotNull(c);
        assertEquals(BigDecimal.valueOf(1.95), c.getTotal());
    }

    @Test
    public void removeFromcart(){
        ModifyCartRequest r = new ModifyCartRequest();
        r.setItemId(1L);
        r.setQuantity(2);
        r.setUsername("test");
        ResponseEntity<Cart> responseEntity = cartController.addTocart(r);
        assertNotNull(responseEntity);
        assertEquals(200,responseEntity.getStatusCodeValue());

        r = new ModifyCartRequest();
        r.setItemId(1L);
        r.setQuantity(1);
        r.setUsername("test");
        responseEntity = cartController.removeFromcart(r);

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        Cart cart = responseEntity.getBody();
        assertNotNull(cart);
        assertEquals(BigDecimal.valueOf(1.95), cart.getTotal());
    }
}
