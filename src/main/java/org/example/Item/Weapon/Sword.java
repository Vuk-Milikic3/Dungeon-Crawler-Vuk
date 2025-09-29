package org.example.Item.Weapon;

public class Sword implements Weapon {
    private final String name;
    private final int damage;

    public Sword(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    @Override
    public String getName() { return name; }

    public int getDamage() { return damage; }

    @Override
    public int damage() { return damage; }

    @Override
    public int stackSize() { return 1; }

    @Override
    public String toString() { return "- Waffe: " + name; }
}


