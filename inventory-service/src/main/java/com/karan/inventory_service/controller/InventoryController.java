package com.karan.inventory_service.controller;

import com.karan.inventory_service.dto.InventoryDTO;
import com.karan.inventory_service.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
@Tag(name = "Inventory Management", description = "Operations for managing and controlling inventory stock and items.")
public class InventoryController {
    private InventoryService inventoryService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new inventory item", description = "Creates a new inventory item for the given SKU code and quantity.")
    public InventoryDTO createInventory(@RequestBody InventoryDTO inventoryDTO){
        return inventoryService.addInventory(inventoryDTO);
    }
    @GetMapping("/skuCode/{skuCode}")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Get inventory details by SKU Code", description = "Returns the inventory details for the given SKU code.")
    public InventoryDTO getInventoryBySkuCode(@PathVariable String skuCode){
        return inventoryService.getInventoryBySkuCode(skuCode);
    }
    @GetMapping("/stock/{skuCode}")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Check if an item is in stock", description = "Checks if the item with the given SKU code is in stock.")
    public boolean isInStock(@PathVariable String skuCode){
        return inventoryService.isInStock(skuCode);
    }
    @PutMapping("/update/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update inventory quantity", description = "Updates the inventory quantity for the given SKU code.")
    public InventoryDTO updateInventory(@PathVariable String skuCode,@RequestBody InventoryDTO inventoryDTO){
        return inventoryService.updateInventory(skuCode,inventoryDTO);
    }
    @DeleteMapping("/skuCode/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete inventory item", description = "Deletes the inventory item with the given SKU code.")
    public void deleteInventory(@PathVariable String skuCode){
        inventoryService.deleteInventory(skuCode);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Get all inventory items", description = "Returns a list of all inventory items.")
    public List<InventoryDTO>findAllInventory(){
        return inventoryService.getAllInventory();
    }


}
