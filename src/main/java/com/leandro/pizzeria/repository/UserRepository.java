package com.leandro.pizzeria.repository;

import com.leandro.pizzeria.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
