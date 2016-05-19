package com.leandro.pizzeria.controller;

import com.leandro.pizzeria.bo.ItemBO;
import com.leandro.pizzeria.model.Item;
import com.leandro.pizzeria.model.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemBO itemBO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        return ResponseEntity.ok(itemBO.findAll());
    }

    @RequestMapping(value = "/byCategory", method = RequestMethod.GET)
    public ResponseEntity findAllByCategory() {

        Map<ItemCategory,List<Item>> map = new HashMap();

        for( ItemCategory category : ItemCategory.values() ){
            map.put(category, itemBO.findAllByCategory(category));
        }

        return ResponseEntity.ok(map);
    }

    @RequestMapping(value = "/byCategory/{category}", method = RequestMethod.GET)
    public ResponseEntity findAllByCategory( @PathVariable ItemCategory category ) {
        return ResponseEntity.ok(itemBO.findAllByCategory(category));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getItem(@PathVariable Integer id) {
        return ResponseEntity.ok(itemBO.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity newItem(@RequestBody Item newItem) {
        return ResponseEntity.ok(itemBO.saveAndFlush(newItem));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity updateItem(@RequestBody Item updatedItem) {
        return ResponseEntity.ok(itemBO.saveAndFlush(updatedItem));
    }
}
