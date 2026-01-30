package com.pbgama.game_leaderboard.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pbgama.game_leaderboard.dto.request.CreatePlayerRequest;
import com.pbgama.game_leaderboard.dto.request.UpdatePlayerRequest;
import com.pbgama.game_leaderboard.model.Player;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.pbgama.game_leaderboard.service.PlayerService;

@RestController
@RequestMapping("/api/players")
public class PlayersController {
    private final PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("")
    public ResponseEntity<Player> createPlayer(@RequestBody CreatePlayerRequest req) {
        try {
            return playerService.createPlayer(req);            
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        try {
            return playerService.getPlayer(id);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody UpdatePlayerRequest req) {
        try {
            return playerService.updatePlayer(id, req);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Player> partialUpdatePlayer(@PathVariable Long id, @RequestBody UpdatePlayerRequest req) {
        try {
            return playerService.updatePlayer(id, req);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id) {
        try {
            return playerService.deletePlayer(id);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Player>> findAllPlayers() {
        try {
            return playerService.findAllPlayers();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}