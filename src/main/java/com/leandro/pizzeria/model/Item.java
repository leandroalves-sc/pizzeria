package com.leandro.pizzeria.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name = "Item.findAllByCategory", query = "SELECT i FROM Item i WHERE i.stockQty > 0 AND i.enabled = 'TRUE' AND i.category = ?1")
public class Item extends BaseEntity{

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    @Column
    @NotNull
    private double price;

    @NotNull
    @Column(name = "stock_qty")
    private int stockQty;

    @Column
    private boolean enabled;

    public Item(){
        this.enabled = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public void addStockQty(int stockQty) {
        this.stockQty += stockQty;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Transient
    public String getNameAndPrice(){
        StringBuilder str = new StringBuilder(getName());

        if(getPrice() > 0){
            str.append(" + $").append(getPrice());
        }

        return str.toString();
    }
}