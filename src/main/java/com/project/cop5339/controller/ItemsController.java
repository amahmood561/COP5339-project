package com.project.cop5339.controller;

import com.project.cop5339.model.Item;
import com.project.cop5339.service.ItemService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ItemsController {
    private final ItemService itemsService;
    public ItemsController(ItemService itemsService) {
        this.itemsService = itemsService;
    }

    public List<Item> getAllItems() {
        return itemsService.getAllItems();
    }
    public Item create(Item item) {
        return itemsService.saveItem(item);
    }
}
