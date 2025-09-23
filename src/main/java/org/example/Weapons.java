package org.example;

public class Weapons {
    private final String name;
    private final int damage;
    private final int stackSize;

    public static Weapons newKunaiStack() { return new Weapons("Kunai (6)", 8, 6); }
    public static Weapons newShurikenStack() { return new Weapons("Shuriken (6)", 7, 6); }
    public static Weapons newKurzschwert() { return new Weapons("Kurzschwert", 12, 1); }
    public static Weapons newKatana() { return new Weapons("Katana", 16, 1); }
    public static Weapons newRiesenschwert() { return new Weapons("Riesenschwert", 20, 1); }
    public static Weapons newWindklinge() { return new Weapons("Windklinge", 18, 1); }

    public Weapons(String name, int damage, int stackSize) {
        this.name = name;
        this.damage = damage;
        this.stackSize = stackSize;
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getStackSize() { return stackSize; }
}


