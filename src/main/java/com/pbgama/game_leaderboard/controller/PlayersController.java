package com.pbgama.game_leaderboard.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.bind.annotation.RestController;

import com.pbgama.game_leaderboard.dto.request.CreatePlayerRequest;
import com.pbgama.game_leaderboard.dto.request.UpdatePlayerRequest;
import com.pbgama.game_leaderboard.model.Player;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
@RestController
@RequestMapping("/api/players")
public class PlayersController {
    private final List<Player> playersData = new CopyOnWriteArrayList<>();

    @PostMapping("")
    public ResponseEntity<String> createPlayer(@RequestBody CreatePlayerRequest req) {
        try {
            var response = createPlayerInternal(req);
            return response.getStatusCode().is2xxSuccessful() ? ResponseEntity.ok("Player has been created successfully, Player Id: " + response.getBody().getId()) 
                : ResponseEntity.badRequest().body("Player creation failed: " + response.getBody());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Player creation failed: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable String id) {
        var result = playersData.stream()
            .filter(player -> player.getId().equals(UUID.fromString(id)))
            .findFirst();

        return result.isEmpty() ? ResponseEntity.notFound().build() 
            : ResponseEntity.ok(result.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String id, @RequestBody UpdatePlayerRequest req) {
        var playerId = UUID.fromString(id);
        var result = playersData.stream()
            .filter(player -> player.getId().equals(playerId))
            .findFirst();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var player = result.get();
        player.setUsername(req.getUsername());
        player.setEmail(req.getEmail());
        player.setCurrentScore(req.getCurrentScore());
        player.setHighestScore(req.getHighestScore());
        player.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok(player);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Player> partialUpdatePlayer(@PathVariable String id, @RequestBody UpdatePlayerRequest req) {
        var playerId = UUID.fromString(id);
        var result = playersData.stream()
            .filter(player -> player.getId().equals(playerId))
            .findFirst();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var player = result.get();
        applyPartialUpdate(player, req);
        player.setUpdateAt(LocalDateTime.now());
        return ResponseEntity.ok(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable String id) {
        return deletePlayerInternal(id) ? ResponseEntity.ok("Player deleted successfully") 
            : ResponseEntity.badRequest().body("Something went wrong");
    }

    private void applyPartialUpdate(Player player, UpdatePlayerRequest req) {
        if (req.getUsername() != null) player.setUsername(req.getUsername());
        if (req.getEmail() != null) player.setEmail(req.getEmail());
        if (req.getCurrentScore() != null) player.setCurrentScore(req.getCurrentScore());
        if (req.getHighestScore() != null) player.setHighestScore(req.getHighestScore());
    }

    private ResponseEntity<Player> createPlayerInternal(CreatePlayerRequest req) 
    {
        if (checkPlayerExistsByUsername(req.getUsername())) {
            return ResponseEntity.badRequest().body(null);
        }

        var newEntry = Player.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .build();

        playersData.add(newEntry);
        return ResponseEntity.ok(newEntry);
    }

    private Boolean deletePlayerInternal(String id) {
        return playersData.removeIf(player -> player.getId().equals(UUID.fromString(id))) ? true : false;
    }

    private Boolean checkPlayerExistsByUsername(String username) 
    {
        return playersData.stream()
            .anyMatch(player -> player.getUsername().equals(username)) ? true: false;
    }
}