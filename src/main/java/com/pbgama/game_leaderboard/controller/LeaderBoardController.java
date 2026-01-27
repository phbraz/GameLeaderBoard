package com.pbgama.game_leaderboard.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LeaderBoardController {

    @PostMapping("/scores")
    public String createScore(@RequestBody String entity) {
        return entity;
    }

    @GetMapping("/leaderboard")
    public List<String> getLeaderboard() {
        return List.of("1", "2", "3");
    }

    @GetMapping("/leaderboard/{playerId}/rank")
    public String getPlayerRank(@RequestBody String entity) {
        return entity;
    }
}
