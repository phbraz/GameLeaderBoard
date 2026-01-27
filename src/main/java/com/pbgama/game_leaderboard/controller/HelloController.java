package com.pbgama.game_leaderboard.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/say-hello")
    public String sayHello() {
        return "Hello from controller";
    }

    @GetMapping("/say-hello/{name}")
    public String sayHelloToName(@PathVariable String name) {
        return "Hello " + name + ", welcome to the soul society"; 
    }
}
