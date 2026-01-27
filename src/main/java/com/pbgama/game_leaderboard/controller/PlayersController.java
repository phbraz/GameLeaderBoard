package com.pbgama.game_leaderboard.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/players")
public class PlayersController {

    @PostMapping("/")
    public String createPlayer(@RequestBody String entity) {
        return entity;
    }

    @GetMapping("/{id}")
    public String getPlayer(@PathVariable String id) {
        return id;
    }

    @PutMapping("/{id}")
    public String updatePlayer(@RequestBody String entity) {        
        return entity;
    }

    @PatchMapping("/{id}")
    public String partialUpdatePlayer(@RequestBody String entity) {
        return entity;
    }

    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable String id) {
        return id;
    }
}
