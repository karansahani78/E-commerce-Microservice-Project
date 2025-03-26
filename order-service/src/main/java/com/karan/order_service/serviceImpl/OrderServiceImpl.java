package com.karan.order_service.serviceImpl;

import com.karan.inventory_service.exception.OutOfStockException;
import com.karan.order_service.customeException.OrderNotFoundException;
import com.karan.order_service.dto.OrderDTO;
import com.karan.order_service.dto.OrderLineItemsDTO;
import com.karan.order_service.model.Order;
import com.karan.order_service.model.OrderLineItems;
import com.karan.order_service.model.OrderStatus;
import com.karan.order_service.repository.OrderRepository;
import com.karan.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        WebClient webClient = webClientBuilder.build(); // Build WebClient instance

        // Check if all items are in stock
        boolean allItemsInStock = orderDTO.getOrderLineItems().stream()
                .allMatch(item -> {
                    try {
                        Boolean isInStock = webClient.get()
                                .uri("http://INVENTORY-SERVICE/api/inventory/stock/{skuCode}", item.getSkuCode()) // Eureka service discovery
                                .retrieve()
                                .bodyToMono(Boolean.class)
                                .toFuture().join(); // Non-blocking alternative to .block()
                        return Boolean.TRUE.equals(isInStock);
                    } catch (Exception e) {
                        throw new RuntimeException("Error checking stock for SKU: " + item.getSkuCode(), e);
                    }
                });

        if (!allItemsInStock) {
            throw new OutOfStockException("One or more items are out of stock!");
        }

        // Convert DTO to Entity
        Order order = Order.builder()
                .orderNumber(orderDTO.getOrderNumber())
                .orderStatus(orderDTO.getOrderStatus())
                .orderLineItems(orderDTO.getOrderLineItems().stream()
                        .map(orderLineItemsDTO -> OrderLineItems.builder()
                                .skuCode(orderLineItemsDTO.getSkuCode())
                                .quantity(orderLineItemsDTO.getQuantity())
                                .price(orderLineItemsDTO.getPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        // Save Order
        Order savedOrder = orderRepository.save(order);

        // Convert Entity to DTO and return
        return OrderDTO.builder()
                .id(savedOrder.getId())
                .orderNumber(savedOrder.getOrderNumber())
                .orderStatus(savedOrder.getOrderStatus())
                .orderLineItems(savedOrder.getOrderLineItems().stream()
                        .map(orderLineItems -> OrderLineItemsDTO.builder()
                                .id(orderLineItems.getId())
                                .skuCode(orderLineItems.getSkuCode())
                                .price(orderLineItems.getPrice())
                                .quantity(orderLineItems.getQuantity())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order not found for given orderId"+orderId));
        return OrderDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderStatus(order.getOrderStatus())
                // Convert list of OrderLineItems to OrderLineItemsDTO
                .orderLineItems(order.getOrderLineItems().stream()
                        .map(item -> OrderLineItemsDTO.builder()
                                .id(item.getId())
                                .skuCode(item.getSkuCode())
                                .price(item.getPrice())
                                .quantity(item.getQuantity())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }


    @Override
    public List<OrderDTO> getAllOrder() {
        return orderRepository.findAll().stream()
                .map(order -> OrderDTO.builder()
                        .id(order.getId())
                        .orderNumber(order.getOrderNumber())
                        .orderStatus(order.getOrderStatus())
                        // Convert OrderLineItems list to DTO list
                        .orderLineItems(order.getOrderLineItems().stream()
                                .map(orderLineItems -> OrderLineItemsDTO.builder()
                                        .id(orderLineItems.getId())
                                        .skuCode(orderLineItems.getSkuCode())
                                        .price(orderLineItems.getPrice())
                                        .quantity(orderLineItems.getQuantity())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList()); // Closing collect to return List<OrderDTO>

    }


    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order not found for given orderId"+orderId));
        existingOrder.setId(orderDTO.getId());
        existingOrder.setOrderNumber(orderDTO.getOrderNumber());
        existingOrder.setOrderStatus(orderDTO.getOrderStatus());

        // update orderline items but we know it is list and dto sot that i have to convert it to entity first
        List<OrderLineItems> updateOrderLineItems=(orderDTO.getOrderLineItems().stream().
                map(dto -> OrderLineItems.builder()
                        .id(dto.getId())
                        .skuCode(dto.getSkuCode())
                        .price(dto.getPrice())
                        .quantity(dto.getQuantity())
                        .build())
                .collect(Collectors.toList()));

        existingOrder.setOrderLineItems(updateOrderLineItems);
        Order savedOrder = orderRepository.save(existingOrder);

        // now convert entity into dto and return it
        return OrderDTO.builder()
                .id(savedOrder.getId())
                .orderStatus(savedOrder.getOrderStatus())
                .orderNumber(savedOrder.getOrderNumber())
                .orderLineItems(savedOrder.getOrderLineItems().stream().
                        map(items -> OrderLineItemsDTO.builder()
                                .id(items.getId())
                                .price(items.getPrice())
                                .skuCode(items.getSkuCode())
                                .quantity(items.getQuantity())
                                .build())
                        .collect(Collectors.toList()))

                .build();
    }


    @Override
    public void deleteOrder(Long orderId) {
        if(!orderRepository.existsById(orderId)){
            throw new OrderNotFoundException("Order not found"+orderId);
        }
        orderRepository.deleteById(orderId);

    }

    @Override
    public OrderDTO cancelOrder(Long orderId) {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Order not found for given id: " + orderId));

            order.setOrderStatus(OrderStatus.CANCELED);
            orderRepository.save(order);

            // Convert entity to DTO and return
            return OrderDTO.builder()
                    .id(order.getId())
                    .orderNumber(order.getOrderNumber())
                    .orderStatus(order.getOrderStatus())
                    // orderline items is actually list so that we need to use stream map
                    .orderLineItems(order.getOrderLineItems().stream().
                            map(orderLineItems -> new OrderLineItemsDTO(orderLineItems.getId(),orderLineItems.getSkuCode(),
                                    orderLineItems.getQuantity(), orderLineItems.getPrice()))
                            .collect(Collectors.toList()))
                    .build();


    }

    @Override
    public void deleteAllOrders() {
        if(orderRepository.count()==0){
            throw new OrderNotFoundException("No orders are available");
        }
        orderRepository.deleteAll();
    }
}