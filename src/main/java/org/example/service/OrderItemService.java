package org.example.service;

import java.util.List;
import java.util.Optional;

import org.example.repository.OrderRepository;
import org.example.repository.OrderItemRepository;

@Service
@Transactional
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(int orderItemId) {
        return orderItemRepository.findById(orderItemId);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(int orderItemId, OrderItem orderItem) {
        Optional<OrderItem> existingOrderItem = orderItemRepository.findById(orderItemId);
        if (existingOrderItem.isPresent()) {
            OrderItem updatedOrderItem = existingOrderItem.get();
            // Update fields as needed (partial update)
            if (orderItem.getMenuItem() != null) {
                updatedOrderItem.setMenuItem(orderItem.getMenuItem());
            }
            if (orderItem.getQuantity() != 0) { // Quantity can be 0. Check if it's different.
                updatedOrderItem.setQuantity(orderItem.getQuantity());
            }
            if (orderItem.getPrice() != null) {
                updatedOrderItem.setPrice(orderItem.getPrice());
            }
            return orderItemRepository.save(updatedOrderItem);
        }
        return null; // Or throw an exception
    }

    public void deleteOrderItem(int orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    public List<OrderItem> getOrderItemsByOrder(int orderId) {
        Order order = orderRepository.findByOrderId(orderId).orElse(null);
        return orderItemRepository.findByOrder(order);
    }


}
