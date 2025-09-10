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

    public void setCurrentHp(int currentHp) {
        this.currentHp = Math.max(0, Math.min(currentHp, maxHp));
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = Math.max(1, maxHp);
        if (currentHp > this.maxHp) {
            currentHp = this.maxHp;
        }
    }

    public int getChakra() {
        return chakra;
    }

    public void setChakra(int chakra) {
        this.chakra = Math.max(0, chakra);
    }

    public int getInventoryUsed() {
        return inventoryUsed;
    }

    public void setInventoryUsed(int inventoryUsed) {
        this.inventoryUsed = Math.max(0, Math.min(inventoryUsed, inventoryCapacity));
    }

    public int getInventoryCapacity() {
        return inventoryCapacity;
    }

    public String getStatusString() {
        return String.format(
                "HP: %d/%d | Chakra: %d | Inventar: %d/%d",
                getCurrentHp(),
                getMaxHp(),
                getChakra(),
                getInventoryUsed(),
                getInventoryCapacity()
        );
    }
}

