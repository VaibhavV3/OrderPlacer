package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.example.entity.MenuItem;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    // 0. Find menus by menu item ids.
    List<MenuItem> findAllByMenuItemIdIn(List<Integer> menuItemIds);

    // 1. Find menu items by restaurant ID
    List<MenuItem> findByRestaurantId(int restaurantId);  // Spring Data JPA derives the query

    // 2. Find menu items by category
    List<MenuItem> findByCategory(String category);

    // 3. Find menu items by item name (case-insensitive "like" query)
    List<MenuItem> findByItemNameContainingIgnoreCase(String itemName);

    // 4. Find menu items priced less than a given price
    List<MenuItem> findByPriceLessThan(java.math.BigDecimal price);

    // 5. Find menu items priced greater than or equal to a given price
    List<MenuItem> findByPriceGreaterThanEqual(java.math.BigDecimal price);

    // 6. Find vegetarian menu items
    List<MenuItem> findByIsVegetarian(boolean isVegetarian);

    // 7. Find vegan menu items
    List<MenuItem> findByIsVegan(boolean isVegan);

    // 8. Find gluten-free menu items
    List<MenuItem> findByIsGlutenFree(boolean isGlutenFree);

    // 9. Find menu items by restaurant ID and category (using @Query)
    @Query("SELECT mi FROM MenuItem mi WHERE mi.restaurantId = :restaurantId AND mi.category = :category")
    List<MenuItem> findByRestaurantIdAndCategory(@Param("restaurantId") int restaurantId, @Param("category") String category);

    // 10. Find menu items by restaurant ID and price less than a given price
    @Query("SELECT mi FROM MenuItem mi WHERE mi.restaurantId = :restaurantId AND mi.price < :price")
    List<MenuItem> findByRestaurantIdAndPriceLessThan(@Param("restaurantId") int restaurantId, @Param("price") java.math.BigDecimal price);

    // 11. Find menu items whose description contains a specific word
    List<MenuItem> findByDescriptionContaining(String description);

    // 12. Find menu items ordered by price (ascending)
    List<MenuItem> findAllByOrderByPriceAsc();

    // 13. Find menu items ordered by price (descending)
    List<MenuItem> findAllByOrderByPriceDesc();

    // 14. Find menu items by name starting with a specific string
    List<MenuItem> findByItemNameStartingWith(String prefix);

    // 15. Find menu items by name ending with a specific string
    List<MenuItem> findByItemNameEndingWith(String suffix);

    // 16. Find menu items whose name contains a certain word
    List<MenuItem> findByItemNameLike(String name);

    // 17. Find all menu items and sort them by creation date
    List<MenuItem> findAllByOrderByCreatedAtAsc();

    // 18. Find all menu items and sort them by update date
    List<MenuItem> findAllByOrderByUpdatedAtAsc();

    // 19. Find all menu items and sort them by creation date, descending
    List<MenuItem> findAllByOrderByCreatedAtDesc();

    // 20. Find all menu items and sort them by update date, descending
    List<MenuItem> findAllByOrderByUpdatedAtDesc();


}

