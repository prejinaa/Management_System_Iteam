package com.example.Management_System.Service;

import com.example.Management_System.Model.Item;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final List<Item> items = new ArrayList<>();

    private long nextId = 1;

    public List<Item> getAllItems() {
        return items;
    }

    public Optional<Item> getItemById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public Item addItem(Item item) {
        item.setId(nextId++);
        items.add(item);
        return item;
    }
    public Item updateItem(Long id, @Valid Item updatedItem) {
        Optional<Item> existingItemOpt = items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            Item existingItem = existingItemOpt.get();
            existingItem.setName(updatedItem.getName());
            existingItem.setDescription(updatedItem.getDescription());
            existingItem.setPrice(updatedItem.getPrice());
            existingItem.setStockQuantity(updatedItem.getStockQuantity());
            return existingItem;
        } else {
            return null;
        }
    }

    public boolean deleteItem(Long id) {
        return items.removeIf(item -> item.getId().equals(id));
    }
}
