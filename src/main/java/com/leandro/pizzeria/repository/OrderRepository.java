package com.leandro.pizzeria.repository;

import com.leandro.pizzeria.model.Order;
import com.leandro.pizzeria.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by leandroalves on 5/15/16.
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUser(User user);
}
