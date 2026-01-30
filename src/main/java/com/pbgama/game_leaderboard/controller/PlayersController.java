package com.pbgama.game_leaderboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.pbgama.game_leaderboard.dto.request.CreatePlayerRequest;
import com.pbgama.game_leaderboard.model.Player;

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
    private List<Player> playersData = new ArrayList<>();

    @PostMapping("")
    public String createPlayer(@RequestBody CreatePlayerRequest req) {
        try {
            var newPlayer = createPlayerInternal(req);
            return "Player has been created successfully, Player Id: " + newPlayer.getId();
        } catch (Exception ex) {
            return "Player creation failed: " + ex.getMessage();
        }
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

    private Player createPlayerInternal(CreatePlayerRequest req) 
    {
        var newEntry = Player.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .build();

        playersData.add(newEntry);
        return newEntry;
    }
}