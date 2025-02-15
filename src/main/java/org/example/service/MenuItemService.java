package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.example.repository.MenuItemRepository;
import org.example.entity.MenuItem;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getAllMenuItemsByRestaurant(int restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public Optional<MenuItem> getMenuItemById(int restaurantId, int id) {
        return menuItemRepository.findById(id).filter(menuItem -> menuItem.getRestaurant_id() == restaurantId);
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(int restaurantId, int id, MenuItem menuItem) {
        Optional<MenuItem> existingMenuItem = menuItemRepository.findById(id).filter(item -> item.getRestaurant_id() == restaurantId);

        if (existingMenuItem.isPresent()) {
            MenuItem updatedMenuItem = existingMenuItem.get();

            // Partial Update Logic (Only update provided fields)
            if (menuItem.getItemName() != null) {
                updatedMenuItem.setItemName(menuItem.getItemName());
            }
            if (menuItem.getDescription() != null) {
                updatedMenuItem.setDescription(menuItem.getDescription());
            }
            if (menuItem.getPrice() != null) {
                updatedMenuItem.setPrice(menuItem.getPrice());
            }
            if (menuItem.getCategory() != null) {
                updatedMenuItem.setCategory(menuItem.getCategory());
            }
            if (menuItem.getImage_url() != null) {
                updatedMenuItem.setImage_url(menuItem.getImage_url());
            }
            if (menuItem.isVegetarian() != menuItem.isVegetarian()) { // Check if changed
                updatedMenuItem.setVegetarian(menuItem.isVegetarian());
            }
            if (menuItem.isVegan() != menuItem.isVegan()) { // Check if changed
                updatedMenuItem.setVegan(menuItem.isVegan());
            }
            if (menuItem.isGlutenFree() != menuItem.isGlutenFree()) { // Check if changed
                updatedMenuItem.setGlutenFree(menuItem.isGlutenFree());
            }
            // ... update other fields as needed ...

            return menuItemRepository.save(updatedMenuItem);
        } else {
            throw new EntityNotFoundException("Menu Item not found with id: " + id + " for restaurant: " + restaurantId);
        }
    }

    public void deleteMenuItem(int restaurantId, int id) {
        menuItemRepository.deleteById(id);
    }

    public List<MenuItem> getMenuItemsByCategory(int restaurantId, String category) {
        return menuItemRepository.findByRestaurantIdAndCategory(restaurantId, category);
    }

    public List<MenuItem> createMenuItemsInBulk(List<MenuItem> menuItems) {
        return menuItemRepository.saveAll(menuItems); // Use saveAll for bulk insert
    }

    // ... other service methods as needed ...

}


