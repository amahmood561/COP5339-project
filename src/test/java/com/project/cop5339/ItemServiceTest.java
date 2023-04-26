
package com.project.cop5339;

import com.project.cop5339.model.Item;
import com.project.cop5339.model.repository.ItemRepository;
import com.project.cop5339.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @Test
    public void testGetAllItemsBySellerId() {
        // Setup mock repository
        List<Item> items = Arrays.asList(
                Item.createItem(1L, "Item 1", BigDecimal.valueOf(10.00), 100L),
                Item.createItem(2L, "Item 2", BigDecimal.valueOf(20.00), 100L),
                Item.createItem(3L, "Item 3", BigDecimal.valueOf(30.00), 200L)
        );
        when(itemRepository.findBySellerId(100L)).thenReturn(items.subList(0, 2));

        // Call service method
        ItemService itemService = new ItemService(itemRepository);
        List<Item> result = itemService.getAllItemsBySellerId(100L);

        // Verify result
        assertEquals(2, result.size());
        assertEquals("Item 1", result.get(0).getName());
        assertEquals(BigDecimal.valueOf(10.00), result.get(0).getPrice());
        assertEquals("Item 2", result.get(1).getName());
        assertEquals(BigDecimal.valueOf(20.00), result.get(1).getPrice());

        // Verify repository interaction
        verify(itemRepository, times(1)).findBySellerId(100L);
        verifyNoMoreInteractions(itemRepository);
    }

    @Test
    public void testSaveItem() {
        // Setup mock repository
        Item itemToSave = Item.createItem(null, "New Item", BigDecimal.valueOf(100.00), 300L);
        Item savedItem = Item.createItem(1L, "New Item", BigDecimal.valueOf(100.00), 300L);
        when(itemRepository.save(itemToSave)).thenReturn(savedItem);

        // Call service method
        ItemService itemService = new ItemService(itemRepository);
        Item result = itemService.saveItem(itemToSave);

        // Verify result
        assertEquals(savedItem, result);

        // Verify repository interaction
        verify(itemRepository, times(1)).save(itemToSave);
        verifyNoMoreInteractions(itemRepository);
    }

}
