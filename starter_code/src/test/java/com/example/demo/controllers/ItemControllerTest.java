package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {

    private ItemController itemController;

    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp () {
        itemController = new ItemController();
        TestUtils.injectObjects(itemController, "itemRepository", itemRepository);
    }

    @Test
    public void get_items () {
        ResponseEntity<List<Item>> items = itemController.getItems();
        Assertions.assertNotNull(items);
        Assertions.assertEquals(200, items.getStatusCodeValue());
    }

    @Test
    public void get_items_by_name () {
        List<Item> i = new ArrayList<Item>(); i.add(new Item());
        when(itemRepository.findByName("Round Widget")).thenReturn(i);
        ResponseEntity<List<Item>> items = itemController.getItemsByName("Round Widget");
        Assertions.assertNotNull(items);
        Assertions.assertEquals(200, items.getStatusCodeValue());

        Assertions.assertEquals(1, items.getBody().size());
    }
}
