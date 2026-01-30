package com.pbgama.game_leaderboard.repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import com.pbgama.game_leaderboard.model.Player;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class PlayerRepository 
{
    private final Map<Long, Player> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public Player save(Player player) {
        if (player.getId() == null) {
            player.setId(idGenerator.incrementAndGet());
        }

        storage.put(player.getId(), player);
        return player;
    }

    public Player findById(Long id) {
        return storage.values()
        .stream().filter(player -> player.getId().equals(id)).findFirst().orElse(null);
    }

    public Player findByUsername(String username) {
        return storage.values().stream().filter(player -> player.getUsername().equals(username)).findFirst().orElse(null);
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }

    public List<Player> findAll() {
        return storage.values().stream().collect(Collectors.toUnmodifiableList());
    }
}
