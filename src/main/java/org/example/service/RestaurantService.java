package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // For transactional operations
import org.example.controller.RestaurantController;
import org.example.repository.*;
import org.example.entity.*;


import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional // All methods in this class are transactional
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(int id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(int id, Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(id);
        if (existingRestaurant.isPresent()) {
            restaurant.setRestaurant_id(id); // Important: Set the ID!
            return restaurantRepository.save(restaurant);
        } else {
            // Handle the case where the restaurant doesn't exist (e.g., throw an exception)
            throw new EntityNotFoundException("Restaurant not found with id: " + id);
        }
    }

    public void deleteRestaurant(int id) {
        restaurantRepository.deleteById(id);
    }

    public List<Restaurant> findByCuisineType(String cuisineType) {
        return restaurantRepository.findByCuisineType(cuisineType);
    }

    public List<Restaurant> findByCity(String city) {
        return restaurantRepository.findByCity(city);
    }



    // Example of a more complex business logic operation
    public Restaurant createOrUpdateRestaurant(Restaurant restaurant) {
        if (restaurant.getRestaurant_id() == 0) { // Check if it's a new restaurant
            return createRestaurant(restaurant);
        } else {
            return updateRestaurant(restaurant.getRestaurant_id(), restaurant);
        }
    }


    public List<Restaurant> searchRestaurants(String keyword, String city) {
        if (keyword != null && city != null) {
            return restaurantRepository.searchRestaurants(keyword, city);
        } else if (keyword != null) {
            return restaurantRepository.findByRestaurantNameContainingIgnoreCase(keyword);
        } else if (city != null) {
            return restaurantRepository.findByCity(city);
        } else {
            return restaurantRepository.findAll(); // Or handle the case where no search criteria are provided.
        }
    }

}
