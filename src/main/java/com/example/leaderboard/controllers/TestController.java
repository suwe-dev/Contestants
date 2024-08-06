package com.example.leaderboard.controllers;

import com.example.leaderboard.dto.TestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/test")
    public TestDto testHello() {
        return new TestDto("It's working...");
    }

}
