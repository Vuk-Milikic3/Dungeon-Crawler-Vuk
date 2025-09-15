package org.example;

public class PlayerStatus {
    private final int currentHp;
    private final int maxHp;
    private final int chakra;
    private final int inventoryUsed;
    private final int inventoryCapacity;

    public PlayerStatus(int currentHp, int maxHp, int chakra, int inventoryUsed, int inventoryCapacity) {
        this.currentHp = currentHp;
        this.maxHp = maxHp;
        this.chakra = chakra;
        this.inventoryUsed = inventoryUsed;
        this.inventoryCapacity = inventoryCapacity;
    }

    @Override
    public String toString() {
        return String.format(
                "HP: %d/%d | Chakra: %d | Inventar: %d/%d",
                currentHp,
                maxHp,
                chakra,
                inventoryUsed,
                inventoryCapacity
        );
    }
}


