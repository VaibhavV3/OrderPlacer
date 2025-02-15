package org.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;

import org.example.service.RestaurantService;
import org.example.entity.Restaurant;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService; // Inject the service

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id) {
        return restaurantService.getRestaurantById(id)
                .map(restaurant -> ResponseEntity.ok(restaurant))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable int id, @RequestBody Restaurant restaurant) {
        try {
            Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);
            return ResponseEntity.ok(updatedRestaurant);
        } catch (EntityNotFoundException ex) { // Catch the exception from the service
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/cuisine/{cuisine}")
    public List<Restaurant> getRestaurantsByCuisine(@PathVariable String cuisine) {
        return restaurantService.findByCuisineType(cuisine);
    }

    @GetMapping("/city/{city}")
    public List<Restaurant> getRestaurantsByCity(@PathVariable String city) {
        return restaurantService.findByCity(city);
    }

    @GetMapping("/search")
    public List<Restaurant> searchRestaurants(@RequestParam(required = false) String keyword, @RequestParam(required = false) String city) {
        return restaurantService.searchRestaurants(keyword, city);
    }

    // ... other controller methods using the service methods
}
