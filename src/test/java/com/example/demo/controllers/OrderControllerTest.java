package com.example.demo.controllers;

//import com.example.demo.TestUtils;
//import com.example.demo.model.persistence.Cart;
//import com.example.demo.model.persistence.Item;
//import com.example.demo.model.persistence.User;
//import com.example.demo.model.persistence.UserOrder;
//import com.example.demo.model.persistence.repositories.OrderRepository;
//import com.example.demo.model.persistence.repositories.UserRepository;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.mock;
//import org.springframework.http.ResponseEntity;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import static org.mockito.Mockito.when;
//
//
///** Rubric - Write tests and meet an acceptable code coverage level.
// * Write proper tests for the above module and meet an acceptable code coverage level.
// * Code coverage of 60% is met through tests. The tests include sanity
// * and regression test cases and take care of negative tests as well.
// * */
//
//public class OrderControllerTest {
//
//    private OrderController orderController;
//
//    private UserRepository userRepo = mock(UserRepository.class);
//
//    private OrderRepository orderRepo = mock(OrderRepository.class);
//
//    @Before
//    public void setUp() {
//        orderController = new OrderController();
//        TestUtils.injectObjects(orderController, "orderRepository", orderRepo);
//        TestUtils.injectObjects(orderController, "userRepository", userRepo);
//
//        Item item = new Item(null,null);
//        item.setId(1L);
//        item.setName("Round Widget");
//        BigDecimal price = BigDecimal.valueOf(2.99);
//        item.setPrice(price);
//        item.setDescription("A widget that is round");
//        List<Item> items = new ArrayList<>();
//        items.add(item);
//
//        User user = new User();
//        Cart cart = new Cart();
//        user.setId(0);
//        user.setUsername("Himanshuvansal");
//        user.setPassword("vansal1332");
//        cart.setId(0L);
//        cart.setUser(user);
//        cart.setItems(items);
//        BigDecimal bigDecimal = BigDecimal.valueOf(2.99);
//        cart.setTotal(bigDecimal);
//        user.setCart(cart);
//        when(userRepo.findByUsername("Himanshuvansal")).thenReturn(user);
//        when(userRepo.findByUsername("vansal")).thenReturn(null);
//    }
//
//
//    @Test
//    public void submitTest(){
//        ResponseEntity<UserOrder> responseEntity = orderController.submit("test");
//        assertNotNull(responseEntity);
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        UserOrder order = responseEntity.getBody();
//        assertNotNull(order);
//        assertEquals(1,order.getItems().size());
//    }
//
//    @Test
//    public void getOrderForUser(){
//        ResponseEntity<List<UserOrder>> responseEntity = orderController.getOrdersForUser("Himanshuvansal");
//        assertNotNull(responseEntity);
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        List<UserOrder> userOrders = responseEntity.getBody();
//        assertNotNull(userOrders);
//    }
//}

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {

    private OrderController orderController;
    private OrderRepository orderRepo = mock(OrderRepository.class);
    private UserRepository userRepo = mock(UserRepository.class);

    @Before
    public void setUp(){
        orderController = new OrderController();
        TestUtils.injectObjects(orderController, "orderRepository", orderRepo);
        TestUtils.injectObjects(orderController, "userRepository", userRepo);

        Item item = new Item();
        item.setId(1L);
        item.setName("Test Item");
        BigDecimal price = BigDecimal.valueOf(1.95);
        item.setPrice(price);
        item.setDescription("Lorum Epson");
        List<Item > items = new ArrayList<>();
        items.add(item);

        User user = new User();
        Cart cart = new Cart();
        user.setId(0);
        user.setUsername("test");
        user.setPassword("testPassword");
        cart.setId(0L);
        cart.setUser(user);
        cart.setItems(items);
        BigDecimal bigDecimal = BigDecimal.valueOf(2.99);
        cart.setTotal(bigDecimal);
        user.setCart(cart);
        when(userRepo.findByUsername("test")).thenReturn(user);
        when(userRepo.findByUsername("testUserName")).thenReturn(null);
    }

    @Test
    public void getOrdersForUser(){

        ResponseEntity<List<UserOrder>> responseEntity = orderController.getOrdersForUser("test");

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<UserOrder> userOrders = responseEntity.getBody();

        assertNotNull(userOrders);
    }

}


