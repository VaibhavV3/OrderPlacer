package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.entity.MenuItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    // Find order items by order
    List<OrderItem> findByOrder(Order order);

    // Find order items by menu item
    List<OrderItem> findByMenuItem(MenuItem menuItem);

    // Add other custom queries as needed

}
