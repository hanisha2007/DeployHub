package com.deployhub.controller;

import com.deployhub.model.User;
import com.deployhub.service.UserService;
import com.deployhub.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.deployhub.dto.LoginResponse;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest){
        return userService.loginUser(loginRequest.getEmail() ,loginRequest.getPassword());
    }
}
