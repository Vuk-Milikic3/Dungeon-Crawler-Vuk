package org.example;

public class Player {
    private final PlayerStatus status;
    private final Room currentRoom;

    public Player() {
        this.status = new PlayerStatus(100, 100, 10, 0, 8);
        this.currentRoom = null;
    }

    public Player(Room startRoom) {
        this.status = new PlayerStatus(100, 100, 10, 0, 8);
        this.currentRoom = startRoom;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public PlayerStatus getPlayerStatus() {
        return getStatus();
    }

    public String getCurrentRoomDescription() {
        return currentRoom == null ? "" : currentRoom.toString();
    }
}

