package org.example.Item.Potion.Heal;

public class LargeHealingPotion implements HealingPotion {
    private final String name = "Grosser-Weisen-Heiltrank";
    private final int healing = 60;

    @Override
    public String getName() { return name; }

    @Override
    public int heal() { return healing; }

    @Override
    public String toString() { return "- Trank: " + name; }
}


