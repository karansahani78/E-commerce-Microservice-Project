package com.karan.order_service.service;

import com.karan.order_service.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    // Place an order
    OrderDTO placeOrder(OrderDTO orderDTO);

    // Get order by order ID
    OrderDTO getOrderById(Long orderId);

    // Get all orders
    List<OrderDTO> getAllOrder();

    // Update an order
    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);

    // Delete order by ID
    void deleteOrder(Long orderId);

    // Cancel an order
    OrderDTO cancelOrder(Long orderId);
    //delete all order
    public void deleteAllOrders();
}
