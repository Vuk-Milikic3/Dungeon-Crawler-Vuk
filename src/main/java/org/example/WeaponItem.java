package org.example;

public class WeaponItem implements Weapon {
    private final String name;
    private final int damage;
    private final int stackSize;

    

    public WeaponItem(String name, int damage, int stackSize) {
        this.name = name;
        this.damage = damage;
        this.stackSize = stackSize;
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getStackSize() { return stackSize; }

    @Override
    public int damage() { return damage; }

    @Override
    public int stackSize() { return stackSize; }

    @Override
    public String toString() {
        return "- Waffe: " + name;
    }
}


