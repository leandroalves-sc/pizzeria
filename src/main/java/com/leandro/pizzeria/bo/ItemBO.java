package com.leandro.pizzeria.bo;

import com.leandro.pizzeria.model.Item;
import com.leandro.pizzeria.model.ItemCategory;
import com.leandro.pizzeria.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by leandroalves on 5/15/16.
 */
@Controller
public class ItemBO {

    @Autowired
    private ItemRepository repo;

    public List<Item> findAll(){
        return repo.findAll(new Sort(Sort.Direction.ASC, "category", "name"));
    }

    public Item saveAndFlush(Item item){
        return repo.saveAndFlush(item);
    }

    public Item findById(Integer id){
        return repo.findOne(id);
    }

    public List<Item> findAllByCategory(ItemCategory category){
        return repo.findAllByCategory(category);
    }

    @Transactional
    public synchronized void addItemStockQty(Item item, int qty){

        item = repo.findOne(item.getId());
        item.addStockQty(qty);

        repo.saveAndFlush(item);
    }
}
