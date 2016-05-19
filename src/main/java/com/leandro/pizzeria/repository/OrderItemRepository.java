package com.leandro.pizzeria.repository;

import com.leandro.pizzeria.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by leandroalves on 5/15/16.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
