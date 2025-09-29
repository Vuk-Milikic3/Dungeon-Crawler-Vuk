package org.example.Player;

public class PlayerHealth {
    private int currentHp;
    private final int maxHp;

    public PlayerHealth(int currentHp, int maxHp) {
        this.currentHp = currentHp;
        this.maxHp = maxHp;
    }

    public int getCurrentHp() { return currentHp; }
    public int getMaxHp() { return maxHp; }

    public void heal(int amount) {
        if (amount <= 0) return;
        int next = currentHp + amount;
        currentHp = Math.min(maxHp, next);
    }

    @Override
    public String toString() {
        return String.format(
                "HP: %d/%d",
                currentHp,
                maxHp
        );
    }
}
