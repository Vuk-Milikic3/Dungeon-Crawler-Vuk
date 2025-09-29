package org.example.Player;

import org.example.Item.Weapon.Weapon;

public class PlayerDamage {
    private final int baseDamage;
    private Weapon equippedWeapon;
    private int chakra;

    public PlayerDamage(int baseDamage, int chakra) {
        this.baseDamage = baseDamage;
        this.chakra = chakra;
    }

    public int getDamage() {
        int bonus = equippedWeapon == null ? 0 : equippedWeapon.damage();
        return baseDamage + bonus;
    }

    public Weapon getEquippedWeapon() { return equippedWeapon; }

    public void equip(Weapon weapon) { this.equippedWeapon = weapon; }

    public int getChakra() { return chakra; }

    public void restoreChakra(int amount) {
        if (amount <= 0) return;
        chakra = chakra + amount;
    }

    @Override
    public String toString() {
        return "Chakra: " + chakra + " | Schaden: " + getDamage();
    }
}