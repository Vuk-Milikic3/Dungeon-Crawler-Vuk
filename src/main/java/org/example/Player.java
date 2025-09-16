package org.example;

public class Player {
    private final PlayerStatus status;
    private Room currentRoom;

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

    public String move(Direction direction) {
        if (currentRoom == null) {
            return "";
        }
        Room nextRoom = currentRoom.getNextRoom(direction);
        if (nextRoom == null) {
            return "Dort ist eine Wand.";
        }
        currentRoom = nextRoom;
        return currentRoom.toString();
    }
}

