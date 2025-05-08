package com.karan.order_service.controller;

import com.karan.order_service.dto.OrderDTO;
import com.karan.order_service.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
@Tag(name = "Order Management", description = "Operations for managing and controlling orders.")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Place a new order", description = "Places a new order with the given order number, order status, and order line items.")
    public OrderDTO placeOrder(@RequestBody OrderDTO orderDTO){
        return orderService.placeOrder(orderDTO);
    }
    @PatchMapping("/id/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Cancel an order", description = "Cancels the order with the given order ID.")
    public OrderDTO cancelOrder(@PathVariable Long orderId){
        return orderService.cancelOrder(orderId);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Get all orders", description = "Returns a list of all orders.")
    public List<OrderDTO>getAllOrders(){
      return  orderService.getAllOrder();
    }
    @GetMapping("/id/{orderId}")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Get order by ID", description = "Returns the order with the given order ID.")
    public OrderDTO getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }
    @PutMapping("/id/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Update order information", description = "Updates the order information for the given order ID.")
    public OrderDTO updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO){
        return orderService.updateOrder(orderId,orderDTO);
    }
    @DeleteMapping("/id/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete order by ID", description = "Deletes the order with the given order ID.")
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);

    }
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete all orders", description = "Deletes all orders.")
    public void deleteAllOrders(){
        orderService.deleteAllOrders();
    }
}
