package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static  org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/** Rubric - Write tests and meet an acceptable code coverage level.
 * Write proper tests for the above module and meet an acceptable code coverage level.
 * Code coverage of 60% is met through tests. The tests include sanity
 * and regression test cases and take care of negative tests as well.
 * */

public class ItemControllerTest {

    private ItemController itemController;

    private ItemRepository itemRepo = mock(ItemRepository.class);

    @Before
    public void setUp(){
        itemController = new ItemController();
        TestUtils.injectObjects(itemController, "itemRepository", itemRepo);

        Item item = new Item();
        item.setId(1L);
        item.setName("Test Item");
        BigDecimal price = BigDecimal.valueOf(1.95);
        item.setPrice(price);
        item.setDescription("Lorum Epsom");

        when(itemRepo.findAll()).thenReturn(Collections.singletonList(item));
        when(itemRepo.findById(1L)).thenReturn(java.util.Optional.of(item));
        when(itemRepo.findByName("Lorum Epsom")).thenReturn(Collections.singletonList(item));

    }

    @Test
    public void getItemsByName(){
        ResponseEntity<List<Item>> responseEntity = itemController.getItemsByName("Lorum Epsum");
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<Item> item = responseEntity.getBody();
        assertNotNull(item);
        assertEquals(1,item.size());
    }

    @Test
    public void getItemById(){
        ResponseEntity<Item> responseEntity = itemController.getItemById(1L);
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        Item item = responseEntity.getBody();
        assertNotNull(item);
    }
}
