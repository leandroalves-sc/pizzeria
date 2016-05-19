package com.leandro.pizzeria.bo;

import com.leandro.pizzeria.model.Order;
import com.leandro.pizzeria.model.OrderItem;
import com.leandro.pizzeria.model.OrderState;
import com.leandro.pizzeria.model.Role;
import com.leandro.pizzeria.model.User;
import com.leandro.pizzeria.repository.ItemRepository;
import com.leandro.pizzeria.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by leandroalves on 5/15/16.
 */
@Service
@Transactional
public class OrderBO{

    @Autowired
    private OrderRepository repo;

    @Autowired
    private ItemBO itemBO;

    @Autowired
    private UserBO userBO;

    public List<Order> findAll(){

        User user = userBO.getLoggedUser();

        if(user != null) {

            if (user.getRole() == Role.ADMIN) {
                return repo.findAll();
            } else {
                return repo.findAllByUser(user);
            }
        }

        return Collections.<Order>emptyList();
    }

    public synchronized Order addOrder(Order order){

        order.setUser(userBO.getLoggedUser());
        order.setDtOrder(Calendar.getInstance().getTime());
        order.setState(OrderState.ORDERED);

        for(OrderItem item : order.getItems() ) {
            item.setOrder(order);
            itemBO.addItemStockQty(item.getItem(), -1);
        }

        return repo.saveAndFlush(order);
    }

    public Order cancelOrderById(Integer orderId){

        User user = userBO.getLoggedUser();
        Order order = repo.findOne(orderId);

        if(order == null){
            throw new RuntimeException("Order not found");
        }

        if(user.getRole() != Role.ADMIN && order.getUser().getId() != user.getId() ){
            throw new RuntimeException("You dont have rights to cancel this order");
        }

        for(OrderItem item : order.getItems() ) {
            item.setOrder(order);
            itemBO.addItemStockQty(item.getItem(), 1);
        }

        order.setState(OrderState.CANCELLED);

        return repo.saveAndFlush(order);
    }

    public Order moveNextStage(Integer orderId ){

        User user = userBO.getLoggedUser();
        Order order = repo.findOne(orderId);

        if(order == null){
            throw new RuntimeException("Order not found");
        }

        if(user.getRole() != Role.ADMIN && order.getUser().getId() != user.getId() ){
            throw new RuntimeException("You dont have rights to cancel this order");
        }

        if(order.getState() == OrderState.CANCELLED || order.getState() == OrderState.DELIVERED){
            throw new RuntimeException("Invalid state");
        }

        order.setState(order.getNextState());
        return repo.saveAndFlush(order);
    }
}