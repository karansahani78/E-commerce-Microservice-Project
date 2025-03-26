package com.karan.order_service.controller;

import com.karan.order_service.dto.OrderDTO;
import com.karan.order_service.service.OrderService;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public OrderDTO placeOrder(@RequestBody OrderDTO orderDTO){
        return orderService.placeOrder(orderDTO);
    }
    @PatchMapping("/id/{orderId}")
    public OrderDTO cancelOrder(@PathVariable Long orderId){
        return orderService.cancelOrder(orderId);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<OrderDTO>getAllOrders(){
      return  orderService.getAllOrder();
    }
    @GetMapping("/id/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }
    @PutMapping("/id/{orderId}")
    public OrderDTO updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO){
        return orderService.updateOrder(orderId,orderDTO);
    }
    @DeleteMapping("/id/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);

    }
    @DeleteMapping()
    public void deleteAllOrders(){
        orderService.deleteAllOrders();
    }
}
