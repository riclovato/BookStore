package com.rick.bookStore.controllers;

import com.rick.bookStore.data.vo.v1.BookVO;
import com.rick.bookStore.data.vo.v1.UserVO;
import com.rick.bookStore.model.Permission;
import com.rick.bookStore.model.User;
import com.rick.bookStore.repositories.PermissionRepository;
import com.rick.bookStore.repositories.UserRepository;
import com.rick.bookStore.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Users Endpoint")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PermissionRepository permissionRepository;
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }
    @PostMapping
    public UserVO create(@RequestBody UserVO user){
        return userService.create(user);
    }
    @PostMapping(value = "/manager")
    public UserVO createManager(@RequestBody UserVO user){
        return userService.createManager(user);
    }
}
