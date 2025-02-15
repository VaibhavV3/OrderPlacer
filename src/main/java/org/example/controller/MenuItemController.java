package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;

import org.example.entity.MenuItem;
import org.example.service.MenuItemService;


import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/menu") // Base path for menu items
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public List<MenuItem> getAllMenuItems(@PathVariable int restaurantId) {
        return menuItemService.getAllMenuItemsByRestaurant(restaurantId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable int restaurantId, @PathVariable int id) {
        return menuItemService.getMenuItemById(restaurantId, id)
                .map(menuItem -> ResponseEntity.ok(menuItem))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MenuItem> createMenuItem(@PathVariable int restaurantId, @RequestBody MenuItem menuItem) {
        menuItem.setRestaurant_id(restaurantId); // Set the restaurant ID
        MenuItem createdMenuItem = menuItemService.createMenuItem(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable int restaurantId, @PathVariable int id, @RequestBody MenuItem menuItem) {

        try {
            MenuItem updatedMenuItem = menuItemService.updateMenuItem(restaurantId, id, menuItem);
            return ResponseEntity.ok(updatedMenuItem);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable int restaurantId, @PathVariable int id) {
        menuItemService.deleteMenuItem(restaurantId, id);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints (optional)

    @GetMapping("/category/{category}") // Menu items by category for a specific restaurant
    public List<MenuItem> getMenuItemsByCategory(@PathVariable int restaurantId, @PathVariable String category) {
        return menuItemService.getMenuItemsByCategory(restaurantId, category);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<MenuItem>> createMenuItemsInBulk(@PathVariable int restaurantId, @RequestBody List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
            menuItem.setRestaurant_id(restaurantId); // Important: Set restaurant ID for each item
        }
        List<MenuItem> createdMenuItems = menuItemService.createMenuItemsInBulk(menuItems);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuItems);
    }

    // ... other endpoints as needed
}
