package com.rick.bookStore.controllers;

import com.rick.bookStore.model.User;
import com.rick.bookStore.repositories.UserRepository;
import com.rick.bookStore.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Users Endpoint")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
}
