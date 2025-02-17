package org.example.entity;

import jakarta.persistence.*;
import java.math.BigDecimal; // Use BigDecimal for currency
import java.time.LocalDateTime;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id") // Database column name (remains the same)
    private int menuItemId;

    @Column(name = "restaurant_id", nullable = false) // Foreign key
    private int restaurantId;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2) // Precision and scale for currency
    private BigDecimal price;

    @Column(name = "category")
    private String category;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "is_vegetarian")
    private boolean isVegetarian;

    @Column(name = "is_vegan")
    private boolean isVegan;

    @Column(name = "is_gluten_free")
    private boolean isGlutenFree;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors (No-args constructor is required by JPA)
    public MenuItem() {}

    public MenuItem(int restaurantId, String itemName, String description, BigDecimal price, String category, String image_url, boolean isVegetarian, boolean isVegan, boolean isGlutenFree) {
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image_url = image_url;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.isGlutenFree = isGlutenFree;
    }


    // Getters and setters for all fields
    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getRestaurant_id() {
        return restaurantId;
    }

    public void setRestaurant_id(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "menuItemId=" + menuItemId +
                ", restaurantId=" + restaurantId +
                ", itemName='" + itemName + '\'' +
                // ... other fields
                '}';
    }
}
