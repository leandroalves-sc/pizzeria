package com.leandro.pizzeria.repository;

import com.leandro.pizzeria.model.Item;
import com.leandro.pizzeria.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByCategory(ItemCategory category);
}
