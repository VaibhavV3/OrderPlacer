package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.example.entity.Order;
import org.example.entity.Restaurant;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Find orders by user
    List<Order> findByUser(String user);

    // Find orders by restaurant
    List<Order> findByRestaurant(Restaurant restaurant);

    // Find orders by status
    List<Order> findByOrderStatus(Order.OrderStatus orderStatus);

    // Find orders by user and status
    List<Order> findByUserAndOrderStatus(String user, Order.OrderStatus orderStatus);

    // Find orders by restaurant and status
    List<Order> findByRestaurantAndOrderStatus(Restaurant restaurant, Order.OrderStatus orderStatus);


    // Find orders by restaurant and status
    List<Order> findByRestaurantAndUser(Restaurant restaurant, String user);

    //Find order by order id
    Optional<Order> findByOrderId(int orderId);

    // Add other custom queries as needed, for example:
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findOrdersBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
