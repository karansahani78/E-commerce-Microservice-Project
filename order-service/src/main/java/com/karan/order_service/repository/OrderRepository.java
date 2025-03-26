package com.karan.order_service.repository;

import com.karan.order_service.dto.OrderLineItemsDTO;
import com.karan.order_service.model.Order;
import com.karan.order_service.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
