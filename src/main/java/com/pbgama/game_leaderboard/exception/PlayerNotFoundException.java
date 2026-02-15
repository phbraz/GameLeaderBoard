package com.pbgama.game_leaderboard.exception;

public class PlayerNotFoundException extends RuntimeException 
{
    public PlayerNotFoundException(String message) {
        super(message);
    }
}
