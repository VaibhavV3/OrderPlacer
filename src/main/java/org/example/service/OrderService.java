package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.example.repository.OrderRepository;
import org.example.repository.RestaurantRepository;
import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.entity.Restaurant;

@Service
@Transactional // Important for data consistency
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public Order createOrder(Order order) {
        // Any pre-processing logic before saving, e.g., setting default status, calculating total
        order.setTotalAmount(order.calculateTotalAmount()); // Calculate before saving
        return orderRepository.save(order);
    }

    public Order updateOrder(int orderId, Order order) {
        Optional<Order> existingOrder = orderRepository.findByOrderId(orderId);
        if (existingOrder.isPresent()) {
            Order updatedOrder = existingOrder.get();
            // Update only the provided fields (partial update)
            if (order.getUser() != null) {
                updatedOrder.setUser(order.getUser());
            }
            if (order.getRestaurant() != null) {
                updatedOrder.setRestaurant(order.getRestaurant());
            }
            if (order.getOrderStatus() != null) {
                updatedOrder.setOrderStatus(order.getOrderStatus());
            }

            // Update order items if provided. Important!
            if (order.getOrderItems() != null) {
                updatedOrder.getOrderItems().clear(); // Clear existing items
                updatedOrder.getOrderItems().addAll(order.getOrderItems()); // Add new ones
                for (OrderItem item : updatedOrder.getOrderItems()) {
                    item.setOrder(updatedOrder); // Important: set the order for each item.
                }
            }

            updatedOrder.setTotalAmount(updatedOrder.calculateTotalAmount()); // Recalculate if items changed.
            return orderRepository.save(updatedOrder);
        }
        return null; // Or throw an exception
    }

    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<Order> getOrdersByUser(String user) {
        return orderRepository.findByUser(user);
    }

    public List<Order> getOrdersByRestaurant(int restaurantId) {
        // Similar to getOrdersByUser, fetch the Restaurant entity first
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
        return orderRepository.findByRestaurant(restaurant); // Replace with your implementation
    }

    public List<Order> getOrdersByRestaurantAndStatus(int restaurantId, Order.OrderStatus orderStatus) {
        // Similar to getOrdersByUser, fetch the Restaurant entity first
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
        return orderRepository.findByRestaurantAndOrderStatus(restaurant,orderStatus); // Replace with your implementation
    }


}
