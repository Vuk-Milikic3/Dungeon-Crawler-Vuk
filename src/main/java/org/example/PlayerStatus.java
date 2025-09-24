package org.example;

public class PlayerStatus {
    private final int currentHp;
    private final int maxHp;
    private final int chakra;

    public PlayerStatus(int currentHp, int maxHp, int chakra) {
        this.currentHp = currentHp;
        this.maxHp = maxHp;
        this.chakra = chakra;
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


