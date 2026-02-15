package com.pbgama.game_leaderboard.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import com.pbgama.game_leaderboard.model.Player;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepository 
{
    private final Map<Long, Player> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public Player save(Player player) {
        if (player.getId() == null) {
            if (existsByUsername(player.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
            player.setId(idGenerator.incrementAndGet());
        } else {
            Player existing = storage.get(player.getId());
            if (existing != null && !existing.getUsername().equals(player.getUsername())) {
                if (existsByUsername(player.getUsername())) {
                    throw new IllegalArgumentException("Username already exists");
                }
            }
        }
        
        storage.put(player.getId(), player);
        return player;
    }

    public Optional<Player> findById(Long id) 
    {
        return storage.values()
            .stream()
            .filter(player -> player.getId().equals(id))
            .findFirst();
    }

    public Optional<Player> findByUsername(String username) 
    {
        return storage.values()
            .stream()
            .filter(player -> player.getUsername()
            .equals(username))
            .findFirst();
    }

    public void deleteById(Long id) 
    {
        storage.remove(id);
    }

    public List<Player> findAll() 
    {
        return storage.values()
            .stream()
            .collect(Collectors.toUnmodifiableList());
    }

    public boolean existsByUsername(String username) 
    {
        return storage.values()
            .stream()
            .anyMatch(player -> player.getUsername()
            .equals(username));
    }
}
