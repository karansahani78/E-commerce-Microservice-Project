package com.karan.inventory_service.serviceImpl;

import com.karan.inventory_service.dto.InventoryDTO;
import com.karan.inventory_service.exception.InventoryNotFoundException;
import com.karan.inventory_service.exception.OutOfStockException;
import com.karan.inventory_service.model.Inventory;
import com.karan.inventory_service.repository.InventoryRepository;
import com.karan.inventory_service.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    @Override
    public InventoryDTO addInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryDTO.getSkuCode())
                .quantity(inventoryDTO.getQuantity())
                .build();
        Inventory saveInventory = inventoryRepository.save(inventory);
        // now convert this entity to dto and return it
        return inventoryDTO.builder()
                .id(saveInventory.getId())
                .skuCode(saveInventory.getSkuCode())
                .quantity(saveInventory.getQuantity())
                .build();
    }

    @Override
    public InventoryDTO getInventoryBySkuCode(String skuCode) {
       Inventory inventory= inventoryRepository.findBySkuCode(skuCode).orElseThrow(()-> new InventoryNotFoundException("Inventory not found for given skucod"+skuCode));
        // then return the dto
        return InventoryDTO.builder()
                .id(inventory.getId())
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .build();
    }

    @Override
    public boolean isInStock(String skuCode) {
        return inventoryRepository.existsBySkuCodeAndQuantityGreaterThan(skuCode,0);
    }

    @Override
    public InventoryDTO updateInventory(String skuCode,InventoryDTO inventoryDTO) {
        Inventory existingInventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(() -> new InventoryNotFoundException("Inventory not found for SKU: " + skuCode));
        existingInventory.setId(inventoryDTO.getId());
        existingInventory.setQuantity(inventoryDTO.getQuantity());
        existingInventory.setSkuCode(inventoryDTO.getSkuCode());
        inventoryRepository.save(existingInventory);
        // now return the updated existingInventory as dto
        return InventoryDTO.builder()
                .id(existingInventory.getId())
                .skuCode(existingInventory.getSkuCode())
                .quantity(existingInventory.getQuantity())
                .build();
    }

    @Override
    public InventoryDTO reduceStock(String skuCode, int quantity) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(()->new InventoryNotFoundException("Inventory not found for given skuCode"+skuCode));
        // logic
        if (inventory.getQuantity() < quantity){
            throw new OutOfStockException("Not enough stock available for SKU: " + skuCode);
        }
        // decrease the quantity
        inventory.setQuantity(inventory.getQuantity()-quantity);
        inventoryRepository.save(inventory);
        // converting to the dto and returning it
        return InventoryDTO.builder()
                .id(inventory.getId())
                .quantity(inventory.getQuantity())
                .skuCode(inventory.getSkuCode())
                .build();

    }

    @Override
    public void deleteInventory(String skuCode) {
        if(!inventoryRepository.existsById(skuCode)){
            throw new InventoryNotFoundException("No inventory found for given skuCode"+skuCode);
        }
        inventoryRepository.deleteById(skuCode);

    }

    @Override
    public List<InventoryDTO> getAllInventory() {
      return inventoryRepository.findAll().stream()
              .map(inventory -> InventoryDTO.builder()
                      .id(inventory.getId())
                      .quantity(inventory.getQuantity())
                      .skuCode(inventory.getSkuCode())
                      .build())
              .collect(Collectors.toList());


    }
}
