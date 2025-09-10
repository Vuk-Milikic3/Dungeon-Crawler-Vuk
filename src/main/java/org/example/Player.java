package org.example;

public class Player {
    private int currentHp;
    private int maxHp;
    private int chakra;
    private int inventoryUsed;
    private final int inventoryCapacity;

    public Player() {
        this.maxHp = 100;
        this.currentHp = 100;
        this.chakra = 10;
        this.inventoryCapacity = 8;
        this.inventoryUsed = 0;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getChakra() {
        return chakra;
    }

    public int getInventoryUsed() {
        return inventoryUsed;
    }

    public int getInventoryCapacity() {
        return inventoryCapacity;
    }

    public PlayerStatus getStatus() {
        return new PlayerStatus(
                getCurrentHp(),
                getMaxHp(),
                getChakra(),
                getInventoryUsed(),
                getInventoryCapacity()
        );
    }

    public PlayerStatus getPlayerStatus() {
        return getStatus();
    }
}

