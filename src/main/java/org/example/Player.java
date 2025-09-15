package org.example;

public class Player {
    private final PlayerStatus status;

    public Player() {
        this.status = new PlayerStatus(100, 100, 10, 0, 8);
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public PlayerStatus getPlayerStatus() {
        return getStatus();
    }
}

