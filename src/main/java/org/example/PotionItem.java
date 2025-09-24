package org.example;

public class PotionItem implements HealingPotion, ChakraPotion {
    private final String name;
    private final int healing;
    private final int chakra;

    

    public PotionItem(String name, int healing, int chakra) {
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
