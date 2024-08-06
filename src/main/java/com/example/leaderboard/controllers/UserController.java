package com.example.leaderboard.controllers;

import com.example.leaderboard.models.UserModel;
import com.example.leaderboard.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public UserModel saveUser(@Valid @RequestBody UserModel userModel) {
        return userService.create(userModel);
    }

}
