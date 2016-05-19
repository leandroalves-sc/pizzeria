package com.leandro.pizzeria.controller;

import com.leandro.pizzeria.bo.OrderBO;
import com.leandro.pizzeria.bo.UserBO;
import com.leandro.pizzeria.model.Order;
import com.leandro.pizzeria.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leandroalves on 5/15/16.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderBO orderBO;

    @Autowired
    private UserBO userBO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll(){
        return ResponseEntity.ok(orderBO.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addOrder(@RequestBody Order order) {

        User user = userBO.getLoggedUser();

        if(user == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            orderBO.addOrder(order);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity cancelOrder(@PathVariable Integer id) {

        User user = userBO.getLoggedUser();

        if(user == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(orderBO.cancelOrderById(id));
    }

    @RequestMapping(value = "/moveNextState", method = RequestMethod.POST)
    public Order moveNextStage(@RequestBody Order order) {
        return orderBO.moveNextStage(order.getId());
    }
}