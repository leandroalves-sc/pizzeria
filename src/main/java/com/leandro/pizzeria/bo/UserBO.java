package com.leandro.pizzeria.bo;

import com.leandro.pizzeria.model.User;
import com.leandro.pizzeria.repository.UserRepository;
import com.leandro.pizzeria.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leandroalves on 5/15/16.
 */
@Service
public class UserBO{

    @Autowired
    private UserRepository repo;

    public User getLoggedUser(){

        String username = SecurityUtils.getCurrentLogin();

        if(username == null){
            return null;
        }

        return repo.findByUsername(username);
    }
}
