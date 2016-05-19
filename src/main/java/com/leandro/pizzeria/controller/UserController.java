package com.leandro.pizzeria.controller;

import com.leandro.pizzeria.bo.UserBO;
import com.leandro.pizzeria.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBO userBO;

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public ResponseEntity getLoggedUser() {

        User user = userBO.getLoggedUser();

        if(user == null){
            ResponseEntity.status(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.ok(user);
    }
}
