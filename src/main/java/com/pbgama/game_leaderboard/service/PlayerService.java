package com.pbgama.game_leaderboard.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pbgama.game_leaderboard.dto.request.CreatePlayerRequest;
import com.pbgama.game_leaderboard.model.Player;
import com.pbgama.game_leaderboard.dto.request.UpdatePlayerRequest;
import com.pbgama.game_leaderboard.repository.PlayerRepository;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class PlayerService 
{
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Player> createPlayer(CreatePlayerRequest req) 
    {
        if (repository.findByUsername(req.getUsername()) != null) {
            return ResponseEntity.badRequest().body(null);
        }

        var newEntry = Player.builder()
            .username(req.getUsername())
            .email(req.getEmail())
            .build();

        repository.save(newEntry);
        return ResponseEntity.ok(newEntry);
    }

    public ResponseEntity<Player> getPlayer(Long id) {
        var player = repository.findById(id);
        return player != null ? ResponseEntity.ok(player) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Player> updatePlayer(Long id, UpdatePlayerRequest req) {
        var player = repository.findById(id);
        if (player == null) 
        {
            return ResponseEntity.notFound().build();
        }
        player.setUsername(req.getUsername());
        player.setEmail(req.getEmail());
        player.setCurrentScore(req.getCurrentScore());
        player.setHighestScore(req.getHighestScore());
        player.setUpdateAt(LocalDateTime.now());
        repository.save(player);

        return ResponseEntity.ok(player);
    }

    public ResponseEntity<Player> deletePlayer(Long id) {
        var player = repository.findById(id);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }
        
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Player>> findAllPlayers() {
        return ResponseEntity.ok(repository.findAll());
    }
}
