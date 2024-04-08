package com.cogent.inventoryservice.controller;

import com.cogent.inventoryservice.payload.Item;
import com.cogent.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/inventory")
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/item")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item data = inventoryService.createItem(item);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> data = inventoryService.getAllItems();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable("itemId") Long itemId) {
        Item data = inventoryService.getItemById(itemId);
        if(data == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable("itemId") Long itemId,
                                                @Valid @RequestBody Item item) {
        Item data = inventoryService.updateItem(itemId, item);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable("itemId") Long itemId) {
        inventoryService.deleteItem(itemId);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }
}
