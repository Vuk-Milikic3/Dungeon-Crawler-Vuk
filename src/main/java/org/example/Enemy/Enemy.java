package org.example.Enemy;

public interface Enemy {
    String getName();
    int getCurrentHealth();
    int getMaxHealth();
    int getDamage();
    void takeDamage(int damage);
    boolean isAlive();
}
