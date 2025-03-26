package com.karan.inventory_service.controller;

import com.karan.inventory_service.dto.InventoryDTO;
import com.karan.inventory_service.service.InventoryService;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {
    private InventoryService inventoryService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryDTO createInventory(@RequestBody InventoryDTO inventoryDTO){
        return inventoryService.addInventory(inventoryDTO);
    }
    @GetMapping("/skuCode/{skuCode}")
    @ResponseStatus(HttpStatus.FOUND)
    public InventoryDTO getInventoryBySkuCode(@PathVariable String skuCode){
        return inventoryService.getInventoryBySkuCode(skuCode);
    }
    @GetMapping("/stock/{skuCode}")
    @ResponseStatus(HttpStatus.FOUND)
    public boolean isInStock(@PathVariable String skuCode){
        return inventoryService.isInStock(skuCode);
    }
    @PutMapping("/update/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryDTO updateInventory(@PathVariable String skuCode,@RequestBody InventoryDTO inventoryDTO){
        return inventoryService.updateInventory(skuCode,inventoryDTO);
    }
    @DeleteMapping("/skuCode/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInventory(@PathVariable String skuCode){
        inventoryService.deleteInventory(skuCode);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<InventoryDTO>findAllInventory(){
        return inventoryService.getAllInventory();
    }


}
