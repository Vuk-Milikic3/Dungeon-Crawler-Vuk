package org.example;

public class ThrowingKnife implements Weapon {
    private final String name;
    private final int damage;
    private final int stackSize = 6;

    public ThrowingKnife(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String getName() { return name; }

    public int getDamage() { return damage; }
    public int getStackSize() { return stackSize; }

    @Override
    public int damage() { return damage; }

    @Override
    public int stackSize() { return stackSize; }

    @Override
    public String toString() { return "- Waffe: " + name; }
}


