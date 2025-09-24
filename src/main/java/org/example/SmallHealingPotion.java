package org.example;

public class SmallHealingPotion implements HealingPotion {
    private final String name = "Kleiner Kyubi-Heiltrank";
    private final int healing = 25;

    @Override
    public String getName() { return name; }

    @Override
    public int heal() { return healing; }

    @Override
    public String toString() { return "- Trank: " + name; }
}


