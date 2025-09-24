package org.example;

public class PlayerStatus {
    private int currentHp;
    private final int maxHp;
    private int chakra;

    public PlayerStatus(int currentHp, int maxHp, int chakra) {
        this.currentHp = currentHp;
        this.maxHp = maxHp;
        this.chakra = chakra;
    }

    public int getCurrentHp() { return currentHp; }
    public int getMaxHp() { return maxHp; }
    public int getChakra() { return chakra; }

    public void heal(int amount) {
        if (amount <= 0) return;
        int next = currentHp + amount;
        currentHp = Math.min(maxHp, next);
    }

    public void restoreChakra(int amount) {
        if (amount <= 0) return;
        chakra = chakra + amount;
    }

    @Override
    public String toString() {
        return String.format(
                "HP: %d/%d | Chakra: %d",
                currentHp,
                maxHp,
                chakra
        );
    }
}


