package org.example;

public class Potions {
    private final String name;
    private final int healing;
    private final int chakraPowerUp;

    public static Potions newHealPotionSmall() {
        return new Potions("Kleiner Kyubi-Heiltrank", 25, 0);
    }

    public static Potions newHealPotionLarge() {
        return new Potions("Großer Weisen-Heiltrank", 60, 0);
    }

    public static Potions newChakraPotionSmall() {
        return new Potions("Kleines Chakra-Elixier", 0, 20);
    }

    public static Potions newChakraPotionLarge() {
        return new Potions("Großes Weisen-Chakra-Elixier", 0, 50);
    }

    public Potions(String name, int healing, int chakraPowerUp) {
        this.name = name;
        this.healing = healing;
        this.chakraPowerUp = chakraPowerUp;
    }

    public String getName() { return name; }
}
