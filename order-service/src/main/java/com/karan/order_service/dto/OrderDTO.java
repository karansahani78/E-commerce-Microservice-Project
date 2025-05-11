package com.karan.order_service.dto;

import com.karan.order_service.model.OrderStatus;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

    private Long id;

    @NotEmpty(message = "Order number cannot be empty")
    private String orderNumber;

    private OrderStatus orderStatus; // Enum for Order Status
    private String message;

    @NotEmpty(message = "Order must contain at least one item")
    private List<OrderLineItemsDTO> orderLineItems;
}
