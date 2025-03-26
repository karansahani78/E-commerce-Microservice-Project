package com.karan.inventory_service.service;

import com.karan.inventory_service.dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    // Add a new inventory item
    InventoryDTO addInventory(InventoryDTO inventoryDTO);

    // Get inventory details by SKU Code
    InventoryDTO getInventoryBySkuCode(String skuCode);

    // Check if an item is in stock
    boolean isInStock(String skuCode);

    // Update inventory quantity
    InventoryDTO updateInventory(String skuCode, InventoryDTO inventoryDTO);

    // Reduce stock when an order is placed
    public InventoryDTO reduceStock(String skuCode, int quantity);

    // Delete inventory item
    void deleteInventory(String skuCode);

    // Get all inventory items
    List<InventoryDTO> getAllInventory();
}
