package com.pbgama.game_leaderboard.service;

import org.springframework.stereotype.Service;

import com.pbgama.game_leaderboard.exception.DuplicateUsernameException;
import com.pbgama.game_leaderboard.exception.PlayerNotFoundException;
import com.pbgama.game_leaderboard.dto.request.CreatePlayerRequest;
import com.pbgama.game_leaderboard.model.Player;
import com.pbgama.game_leaderboard.dto.request.UpdatePlayerRequest;
import com.pbgama.game_leaderboard.repository.PlayerRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) 
    {
        this.repository = repository;
    }

    public Player createPlayer(CreatePlayerRequest req) 
    { 
        if (repository.findByUsername(req.getUsername()).isPresent()) {
            throw new DuplicateUsernameException("Username '" + req.getUsername() + "' already exists");
        }
        
        Player newPlayer = Player.builder()
            .username(req.getUsername())
            .email(req.getEmail())
            .currentScore(0)
            .highestScore(0)
            .build();
        
        return repository.save(newPlayer);
    }
    
    public Player getPlayer(Long id) 
    {
        return repository.findById(id)
            .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
    }
    
    public Player updatePlayer(Long id, UpdatePlayerRequest req) 
    {
        Player player = getPlayer(id);
        
        player.setUsername(req.getUsername());
        player.setEmail(req.getEmail());
        player.setCurrentScore(req.getCurrentScore());
        player.setHighestScore(req.getHighestScore());
        player.setUpdatedAt(LocalDateTime.now());
        
        return repository.save(player);
    }
    
    public void deletePlayer(Long id) 
    { 
        getPlayer(id);  // Throws if not found
        repository.deleteById(id);
    }
    
    public List<Player> findAllPlayers() 
    { 
        return repository.findAll();
    }
}
