package com.pbgama.game_leaderboard.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pbgama.game_leaderboard.dto.request.CreatePlayerRequest;
import com.pbgama.game_leaderboard.dto.request.UpdatePlayerRequest;
import com.pbgama.game_leaderboard.model.Player;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.pbgama.game_leaderboard.service.PlayerService;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/players")
public class PlayersController {
    private final PlayerService playerService;

    public PlayersController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@Valid @RequestBody CreatePlayerRequest req) {
        Player player = playerService.createPlayer(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(player);  // 201
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        Player player = playerService.getPlayer(id);
        return ResponseEntity.ok(player);  // 200
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody UpdatePlayerRequest req) {
        Player player = playerService.updatePlayer(id, req);
        return ResponseEntity.ok(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();  // 204
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAllPlayers() {
        List<Player> players = playerService.findAllPlayers();
        return ResponseEntity.ok(players);
    }
}