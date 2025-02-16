package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.example.entity.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    // 1. Find restaurants by cuisine type
    List<Restaurant> findByCuisineType(String cuisineType);

    // 2. Find restaurants by city
    List<Restaurant> findByCity(String city);

    // 3. Find restaurants by name (case-insensitive "like" query)
    List<Restaurant> findByRestaurantNameContainingIgnoreCase(String restaurantName);

    // 4. Find restaurants with names containing a specific string (case-insensitive) OR located in a specific city
    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.restaurantName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR r.city = :city")
    List<Restaurant> searchRestaurants(@Param("keyword") String keyword, @Param("city") String city);

    Restaurant findByRestaurantId(int restaurantId);
}

