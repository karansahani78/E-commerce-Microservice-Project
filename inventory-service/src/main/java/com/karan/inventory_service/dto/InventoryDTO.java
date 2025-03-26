package com.karan.inventory_service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InventoryDTO {
    private String id;
    private String skuCode;
    private Integer quantity;
}
