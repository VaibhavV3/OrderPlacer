package org.example.entity;

import jakarta.persistence.*; // Important: Use jakarta.persistence for Spring Boot 3+
import java.time.LocalDateTime; // Use java.time for modern date/time handling

@Entity
@Table(name = "restaurants") // Optional: Specify table name if different from class name
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing primary key
    @Column(name = "restaurant_id")
    private int restaurantId;

    @Column(name = "restaurant_name", nullable = false) // Optional: Specify column name and constraints
    private String restaurantName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode; // Use camelCase for Java conventions

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "description")
    private String description;

    @Column(name = "operating_hours")
    private String operatingHours;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at", updatable = false) // Mark created_at as non-updatable
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    // Constructors (No-args constructor is required by JPA)
    public Restaurant() {}

    public Restaurant(String restaurantName, String address, String city, String state, String zipCode, String phoneNumber, String cuisineType, String description, String operatingHours, String imageUrl) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.cuisineType = cuisineType;
        this.description = description;
        this.operatingHours = operatingHours;
        this.imageUrl = imageUrl;
    }


    // Getters and setters for all fields (Important!)
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    // Important: Add @PrePersist and @PreUpdate methods for timestamps
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
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                // ... other fields
                '}';
    }
}
