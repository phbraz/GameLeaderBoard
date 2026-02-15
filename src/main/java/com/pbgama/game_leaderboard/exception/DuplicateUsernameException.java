package com.pbgama.game_leaderboard.exception;

public class DuplicateUsernameException extends RuntimeException 
{
    public DuplicateUsernameException(String message) {
        super(message);
    }
}
