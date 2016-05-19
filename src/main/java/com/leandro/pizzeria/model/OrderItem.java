package com.leandro.pizzeria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by leandroalves on 5/15/16.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderItem extends BaseEntity{

    @JsonIgnore
    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}