package org.example;

public class Potions implements Potion {
    private final String name;
    private final int healing;
    private final int chakra;

    public static Potions newHealPotionSmall() { return new Potions("Kleiner Kyubi-Heiltrank", 25, 0); }
    public static Potions newHealPotionLarge() { return new Potions("Grosser Weisen-Heiltrank", 60, 0); }
    public static Potions newChakraPotionSmall() { return new Potions("Kleines Chakra-Elixier", 0, 20); }
    public static Potions newChakraPotionLarge() { return new Potions("Grosses Weisen-Chakra-Elixier", 0, 50); }

    public Potions(String name, int healing, int chakra) {
        this.name = name;
        this.healing = healing;
        this.chakra = chakra;
    }

    @Override
    public String getName() { return name; }

    @Override
    public int heal() { return healing; }

    @Override
    public int chakraPowerUp() { return chakra; }

    @Override
    public String toString() {
        return "- Trank: " + name;
    }
}
