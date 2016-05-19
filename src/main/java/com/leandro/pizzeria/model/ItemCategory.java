package com.leandro.pizzeria.model;

/**
 * Created by leandroalves on 5/15/16.
 */
public enum ItemCategory {

    SIZE("Size"),
    BASE("Pizza crust"),
    SAUCE("Sauce"),
    TOPPING("Topping");

    private String name;

    ItemCategory(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
