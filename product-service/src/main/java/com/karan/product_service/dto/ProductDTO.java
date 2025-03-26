package com.karan.product_service.dto;
import lombok.*;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}