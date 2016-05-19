package com.leandro.pizzeria.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by leandroalves on 5/15/16.
 */
@Entity(name = "orders")
public class Order extends BaseEntity{

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @Column(name = "dt_order")
    private Date dtOrder;

    @Column
    private double total;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> items;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDtOrder() {
        return dtOrder;
    }

    public void setDtOrder(Date dtOrder) {
        this.dtOrder = dtOrder;
    }

    public OrderState getState() {
        return state;
    }

    public OrderState getNextState(){
        return state.getNextState();
    }

    public String getPrettyStateName() {
        return getState() == null ? "" : getState().prettyName();
    }

    public String getPrettyNextStateName(){
        return getNextState() == null ? "" : getNextState().prettyName();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {

        BigDecimal total = BigDecimal.ZERO;

        for(OrderItem item : items ) {
            total = total.add(new BigDecimal(Double.toString(item.getItem().getPrice())));
        }

        this.total = total.doubleValue();
        this.items = items;
    }

    public String getSize() {
        return getByItemCategory(ItemCategory.SIZE);
    }

    public String getBase() {
        return getByItemCategory(ItemCategory.BASE);
    }

    public String getSauce() {
        return getByItemCategory(ItemCategory.SAUCE);
    }

    public String getToppings() {
        return getByItemCategory(ItemCategory.TOPPING);
    }

    public String getByItemCategory(ItemCategory category){

        return items.stream()
                .filter( i -> i.getItem().getCategory() == category)
                .map(m->m.getItem().getName())
                .collect(Collectors.joining(", "));
    }
}