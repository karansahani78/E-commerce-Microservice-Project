package com.karan.inventory_service.repository;

import com.karan.inventory_service.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory,String> {
    Optional<Inventory> findBySkuCode(String skuCode);
    // check if an inventory item exists with a given SKU code and has more than the specified quantity.
    boolean existsBySkuCodeAndQuantityGreaterThan(String skuCode, int quantity);
}
