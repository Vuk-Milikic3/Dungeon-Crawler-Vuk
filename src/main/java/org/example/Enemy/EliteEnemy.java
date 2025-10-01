package org.example.Enemy;

public class EliteEnemy implements Enemy {
    private final String name;
    private final int maxHealth;
    private int currentHealth;
    private final int damage;

    public EliteEnemy(String name, int maxHealth, int damage) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.damage = damage;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void takeDamage(int amount) {
        if (amount <= 0) return;
        currentHealth = Math.max(0, currentHealth - amount);
    }

    @Override
    public boolean isAlive() {
        return currentHealth > 0;
    }

    @Override
    public String toString() {
        return name + ": " + currentHealth + "/" + maxHealth + " HP";
    }
}